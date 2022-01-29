#!/bin/sh
mysqladmin -h localhost -P 3306 --protocol=tcp -u root --password=Tommijgqb#19 drop hc_apartmentdb
mysqladmin -h localhost -P 3306 --protocol=tcp -u root --password=Tommijgqb#19 create hc_apartmentdb
mysql -h localhost -P 3306 --protocol=tcp -u root --password=Tommijgqb#19 hc_apartmentdb < ./hc_apartment.sql
mysqlshow -h localhost -P 3306 --protocol=tcp -u root --password=Tommijgqb#19
mysqlshow -h localhost -P 3306 --protocol=tcp -u root --password=Tommijgqb#19 hc_apartmentdb