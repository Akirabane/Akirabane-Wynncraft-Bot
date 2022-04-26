# Akirabane-Wynncraft-Bot
Bot designed by Akirabane for Wynncraft oukkie's guild.

REQUIREMENTS :

  Basic JAVA, Programmation & API USAGE knowledges.
  BUILT ON MAVEN -> JDK Java 8 / JAVA level 8
      <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
      </properties>
  
  Pom.xml should have all those dependencies:
      <dependencies>
        <dependency>
            <groupId>net.dv8tion</groupId>
            <artifactId>JDA</artifactId>
            <version>VERSION</version>
        </dependency>
        <dependency>
            <groupId>com.github.Bedo9041</groupId>
            <artifactId>jWynn</artifactId>
            <version>VERSION</version>
        </dependency>
        <dependency>
            <groupId>com.github.sedmelluq</groupId>
            <artifactId>lavaplayer</artifactId>
            <version>VERSION</version>
        </dependency>
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>VERSION</version>
        </dependency>
    </dependencies>
    
Need importantly those 2 repositories :
            <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
        <repository>
            <id>dv8tion</id>
            <name>m2-dv8tion</name>
            <url>https://m2.dv8tion.net/releases</url>
        </repository>
