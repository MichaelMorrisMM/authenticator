# authenticator
REST micro service responsible for creating and authenticating users

# Deployment
## Bootable Jar
1. Use the provided Maven wrapper and run command `clean package -DskipTests` to build a bootable jar 
(Maven by default will save this artifact in the `target` folder)
2. Run the jar with `java -jar <jar name> --spring.profiles.active=prod`
(Alternatively, use the `dev` Spring profile for development deployments)

# Technology Stack
1. Spring Boot
2. Maven
