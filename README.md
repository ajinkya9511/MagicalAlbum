The Magical Album & Films - Billing Software

Description

This is a simple billing software created for The Magical Album & Films business, designed to manage billing services like Cinematic Video, Traditional Video, and Album creation. The software allows users to input customer details, select services, and print invoices. The software also includes an option to save the invoice as a text file.

Features

Enter customer details (Name, Mobile No, Job No, Date).

Select services (Cinematic Video, Traditional Video, Album).

Save the invoice as a text file.

Print the invoice.

Display shop contact information.


Requirements

To run this software, you need to have Java Runtime Environment (JRE) installed on your system.

Software Requirements

JRE version 8 or higher


Developer Requirements

JDK version 8 or higher (for compiling and creating the JAR file)


Installation

1. Running the Pre-Built JAR File

To run the application on a machine with only JRE:

1. Download the JAR file from the release section or compile it yourself (see "Building from Source").


2. Open a terminal or command prompt and navigate to the directory where the JAR file is saved.


3. Run the following command:

java -jar BillingSoftware.jar

Make sure that the JRE is properly installed, and the system recognizes the java command.



2. Building from Source

If you want to compile the project and create the JAR yourself:

1. Install JDK (Java Development Kit).


2. Download the source code.


3. Compile the code by running the following commands in the terminal/command prompt:

javac BillingSoftware.java
jar cfe BillingSoftware.jar BillingSoftware *.class


4. You will have a BillingSoftware.jar file, which you can now run on any machine with JRE using:

java -jar BillingSoftware.jar



How to Use

1. Launch the Application by double-clicking on the JAR file or running the java -jar BillingSoftware.jar command.


2. Input Customer Details:

Name

Date (auto-generated, but can be changed)

Mobile Number

Job Number



3. Select Services:

Cinematic Video

Traditional Video

Album
