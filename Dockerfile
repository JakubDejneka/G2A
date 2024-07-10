# Use an official Maven image to build the project
FROM maven:3.8.1-openjdk-11 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file and the source code to the container
COPY pom.xml .
COPY src ./src

# Run the Maven build
RUN mvn clean build

# Use an official OpenJDK runtime image
FROM openjdk:11-jre-slim

# Set environment variables for Chrome and ChromeDriver versions
ENV CHROME_VERSION="latest"
ENV CHROMEDRIVER_VERSION="latest"

# Use an official Microsoft Windows Server Core image
FROM mcr.microsoft.com/windows/servercore/insider:10.0.26212.5000

# Use PowerShell as the shell for executing commands
SHELL ["powershell", "-Command"]

# Install Chocolatey
RUN Set-ExecutionPolicy Bypass -Scope Process -Force; \
    [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; \
    iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))

# Example: Install Git using Chocolatey
RUN choco install git -y

# Install Google Chrome and ChromeDriver using Chocolatey
ARG CHROME_VERSION="latest"
ARG CHROMEDRIVER_VERSION="latest"

RUN choco install googlechrome -y --version=$env:CHROME_VERSION; \
    choco install chromedriver -y --version=$env:CHROMEDRIVER_VERSION


# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/G2AProjekt-1.0-SNAPSHOT.jar app.jar

# Command to run the application
CMD ["java", "-jar", "app.jar"]
