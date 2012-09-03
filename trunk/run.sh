### Installation Notes AdServer v0.1

## Technical requirements:
# a. Any type of *Nix operation system (with linux kernel). Check architecture:
uname -a

# b. JVM version more than 1.5. Check java:
java -version

## Instruction (step by step):
# 1. Checkout the last version of jax-rs-adserver project:
#    (http://code.google.com/p/jax-rs-adserver/source/checkout).
svn checkout http://jax-rs-adserver.googlecode.com/svn/trunk/ jax-rs-adserver-read-only

# 2. Install ant build system on your machine:
#    An example for ubuntu system it is command `apt-get install ant`. Build project:
ant build.jar & mv adserver.jar lib/

### Not required steps:
# 3. Also, need to install and configure PostgreSQL database.
#    Need to define DB options (username, password and DB name) into file JDBCProvider.java.
#    See enum com.ad.api.JDBCProvider: POSTGRESQL_ADDB for details.
# 4. Call script dump.sql

# 5. The last step below (start RESTful web service):
java -cp $(echo lib/*.jar | tr ' ' ':') com.ad.api.AdServer

# 6. Go to link: http://localhost:9998/ad/json
# AdService: /ad/json; /ad/html
# ReportService: /reporting/campaign
# StatService: /stat/clicks
