# Crypto

Crypto is a web application written in Java. It serves as an exercise for our application developer training. The aim was to master the following concepts:
- DAO
- Git tags
- H2
- JSP
- JSTL
- Lombok
- Maven
- Servlets

## Link to the project

http://crypto-env.eba-ky6yb5dx.eu-west-3.elasticbeanstalk.com/

## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

1. Use Linux OS.
2. Install and run Tomcat 9.x : https://www.rosehosting.com/blog/how-to-install-tomcat-on-ubuntu-20-04/
3. Install and run MySQL 8.x
    ```sh
    sudo apt-get update
    sudo apt-get install mysql-server -y
    sudo nano /etc/mysql/mysql.conf.d/mysqld.cnf and
    replace bind-address = 127.0.0.1 by bind-address = 0.0.0.0
    ```
   - click on inbound Rules in security group & add arura/mysql access from anywhere
4. Install Maven : https://maven.apache.org/install.html

### Installation

1. Clone the repo
2. Run script_ddl.sql and script_data.sql
3. Generate fat war maven
    ```sh
    mvn install -DskipTests
    ```
4. Add fat war in webapp directory of Tomcat

## Roadmap

### Features not yet implemented

- 100% completed

## License
[MIT](https://choosealicense.com/licenses/mit/)