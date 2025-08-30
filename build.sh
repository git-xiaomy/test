#!/bin/bash

# Spring Boot Scaffold Build Script

echo "🚀 Building Spring Boot Scaffold..."

# Clean and compile
echo "📦 Cleaning and compiling..."
mvn clean compile

if [ $? -ne 0 ]; then
    echo "❌ Compilation failed!"
    exit 1
fi

# Package
echo "📦 Packaging application..."
mvn package -DskipTests

if [ $? -ne 0 ]; then
    echo "❌ Packaging failed!"
    exit 1
fi

echo "✅ Build completed successfully!"
echo "📁 JAR file location: target/springboot-scaffold-1.0.0.jar"
echo ""
echo "🐳 To run with Docker:"
echo "   docker-compose up -d"
echo ""
echo "☕ To run with Java:"
echo "   java -jar target/springboot-scaffold-1.0.0.jar"
echo ""
echo "🌐 Application will be available at: http://localhost:8080/api"