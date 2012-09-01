package com.ad.api.ws;

import com.ad.api.jdbc.JDBCProvider;
import com.ad.core.Builder;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
public class ReportResponse {
    public final String pub_id;
    public final Set<Campaign> campaign_id;

    public ReportResponse(String pub_id, Set<Campaign> campaign_id) {
        this.pub_id = pub_id;
        this.campaign_id = campaign_id;
    }

    @XmlRootElement
    public static class Campaign
    {
        public final String campaign_id;
        public final Integer impressions;
        public final Integer clicks;
        public final Integer completed;

        public Campaign(String campaign_id, Integer impressions, Integer clicks, Integer completed) {
            this.campaign_id = campaign_id;
            this.impressions = impressions;
            this.clicks = clicks;
            this.completed = completed;
        }

        public static Campaign from(ResultSet resultSet) throws SQLException {
            return new Campaign(
                    resultSet.getString("campaign_id"),
                    Integer.valueOf(resultSet.getInt("impressions")),
                    Integer.valueOf(resultSet.getInt("clicks")),
                    Integer.valueOf(resultSet.getInt("completed"))
            );
        }
    }

    public static ReportResponseBuilder from(ReportRequest reportRequest)
    {
        return new ReportResponseBuilder(reportRequest);
    }

    public static ReportResponseBuilder fake()
    {
        ReportResponseBuilder builder = new ReportResponseBuilder();
        builder.isFakeResponse = true;
        return builder;
    }

    public static class ReportResponseBuilder implements Builder<ReportResponse> {
        private boolean isFakeResponse = false;
        private JDBCProvider jdbcProvider = null;
        private ReportRequest reportRequest;
        private String SELECT_CAMPAIGN =
                "SELECT campaign_id\n" +
                "     , pub_id\n" +
                "     , impressions\n" +
                "     , clicks\n" +
                "     , completed\n " +
                "  FROM campaign\n" +
                " WHERE pub_id=%s AND campaign_id=%s";

        public ReportResponseBuilder(ReportRequest reportRequest) {
            this.reportRequest = reportRequest;
        }

        private ReportResponseBuilder() {
        }

        public ReportResponseBuilder setProvider(JDBCProvider jdbcProvider) {
            this.jdbcProvider = jdbcProvider;
            return this;
        }

        @Override
        public ReportResponse build() {
            if (isFakeResponse){
                return buildFakeResponse();
            } else if (jdbcProvider != null)
            {
                Set<Campaign> campaigns = new HashSet<Campaign>();
                try {
                    Statement statement = jdbcProvider.statement();
                    ResultSet resultSet = statement.executeQuery(String.format(SELECT_CAMPAIGN, reportRequest.pubId, reportRequest.campaignId));
                    while (resultSet.next())
                    {
                        campaigns.add(Campaign.from(resultSet));
                    }
                    resultSet.close();
                    statement.close();
                } catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }
                return new ReportResponse(reportRequest.pubId, campaigns);
            }
            throw new NullPointerException();
        }

        private ReportResponse buildFakeResponse() {
            Set<Campaign> campaignSet = new HashSet<Campaign>();
            campaignSet.add(new Campaign("", 0, 0, 0));
            return new ReportResponse("", campaignSet);
        }
    }
}
