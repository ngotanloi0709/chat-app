# __Introduction__
## __About__
This project is made as the final report for the Course "Computer Network Programming" at Ton Duc Thang University
## __Technique__
- Multithread programming
- TCP/IP Socket (Local Network)
- AES Encryption
- Database (MySQL)
- JavaSwing GUI
## __Feature__
### Group chat
- Send/Receive messages in a group
- Clear/Load Chat Log from Database
- Encrypt and Decrypt messages
### Upload/Download File
- Upload File to Server 
- Get the uploaded File

### User Management
- Show Users Information
- Add/Remove Users
- Edit Users Information

## __Contributors__
### Ngo Tan Loi (ngotanloi0709@gmail.com)
### Le Vu Phuong Quang (ql7769663@gmail.com)
# __Installation__
This project requires below technologies to implement:
- MySQL 8
- Netbean 16 (JDK 19) (Other IDE such as IntelliJ)

## __Setup__
- Clone this repository
- Open the repository in your IDE
- Import `chat_app.sql` to MySQL Database
- Config the Database information in `src/core/Database.java`
- Build the project
- Run the `ServerInitialize.java` to start the Server
- Run the `App.java` to start a client

## __Optional__
- Flatlaf Dark Theme
- Jetbrain Mono Font

## __Program's workflow__
`ServerInitialize.java` turn on the Server to accept the Client Socket, also create a Client represent to it's own
`App.java` turn on a Client each time it's started. The client search for Server Socket constantly and automatically. 

- The Chat feature only available when it's connected to the Server
- The User Management feature only available to User has Role = 1 and the Server

# __References__
- Witt Code - "Java Socket Programming - Send and Download Files Between Client and Server" - (https://youtu.be/GLrlwwyd1gY)
- Witt Code - "Java Socket Programming - Multiple Clients Chat" - (https://www.youtube.com/watch?v=gLfuZrrfKes)
- Coding with John - "Multithreading in Java Explained in 10 Minutes" - (https://www.youtube.com/watch?v=r_MbozD32eo)
- ChatGPT



