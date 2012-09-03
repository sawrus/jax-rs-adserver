package com.ad.impl;

import com.ad.api.Builder;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@XmlRootElement
public class ADResponse {
    public final Extra extra;
    public final List<Networks> networks;
    public final List<INetworks> inetworks;

    public ADResponse(Extra extra, List<Networks> networks, List<INetworks> inetworks) {
        this.extra = extra;
        this.networks = networks;
        this.inetworks = inetworks;
    }

    @Override
    public String toString() {
        return "ADResponse{" +
                "extra=" + extra +
                ", networks=" + networks +
                ", inetworks=" + inetworks +
                '}';
    }

    @XmlRootElement
    public static class Extra {
        public final String stat_hostname;

        public Extra(String stat_hostname) {
            this.stat_hostname = stat_hostname;
        }

        @Override
        public String toString() {
            return "Extra{" +
                    "stat_hostname='" + stat_hostname + '\'' +
                    '}';
        }
    }

    @XmlRootElement
    public static class Networks {
        public final String id;
        public final String position;

        public Networks(String id, String position) {
            this.id = id;
            this.position = position;
        }

        @Override
        public String toString() {
            return "Networks{" +
                    "id='" + id + '\'' +
                    ", position='" + position + '\'' +
                    '}';
        }
    }

    @XmlRootElement
    public static class INetworks {
        public final String id;
        public final String type;
        public final Integer priority;
        public final String position;
        public final Options options;

        public INetworks(String id, String type, Integer priority, String position, Options options) {
            this.id = id;
            this.type = type;
            this.priority = priority;
            this.position = position;
            this.options = options;
        }

        @Override
        public String toString() {
            return "INetworks{" +
                    "id='" + id + '\'' +
                    ", type='" + type + '\'' +
                    ", priority=" + priority +
                    ", position='" + position + '\'' +
                    ", options=" + options +
                    '}';
        }

        @XmlRootElement
        public static class Options {
            public final String hostName;
            public final String url;
            public final boolean unlocker;
            public final Set<String> params;

            public Options(String hostName, String url, boolean unlocker, Set<String> params) {
                this.hostName = hostName;
                this.url = url;
                this.unlocker = unlocker;
                this.params = params;
            }
        }
    }

    public static ADResponseBuilder fake() {
        ADResponseBuilder builder = new ADResponseBuilder();
        builder.isFakeResponse = true;
        return builder;
    }

    public static class ADResponseBuilder implements Builder<ADResponse> {
        private boolean isFakeResponse = false;

        private ADResponseBuilder() {
        }

        @Override
        public ADResponse build() {
            if (isFakeResponse) {
                return buildFakeResponse();
            }

            //todo: Need to implement filling ADResponse
            throw new NullPointerException();
        }

        private ADResponse buildFakeResponse() {
            Extra extra = new Extra("");
            List<Networks> networks = Arrays.asList(new Networks("", ""));
            HashSet<String> params = new HashSet<String>();
            params.add("");
            INetworks.Options options = new INetworks.Options("", "", true, params);
            List<INetworks> inetworks = Arrays.asList(new INetworks("","", 0, "", options));
            return new ADResponse(extra, networks, inetworks);
        }
    }
}
