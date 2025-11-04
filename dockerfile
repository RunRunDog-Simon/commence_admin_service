FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/commerce_admin_service-0.0.1-SNAPSHOT.jar /app/commerce_admin_service.jar

EXPOSE 8080

CMD ["java", "-jar", "commerce_admin_service.jar"]
