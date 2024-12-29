# Linkify - Documentation

## Overview
**ChatApplication** is a real-time chat application built using **Spring Boot** and **WebSocket** technologies. The application allows users to send and receive messages instantly with the ability to broadcast join/leave notifications. It uses **STOMP** and **SockJS** protocols for handling WebSocket communications, ensuring real-time message delivery.

---

## Features
- **Real-time Messaging**: Users can send and receive messages instantly, ensuring a smooth, live conversation.
- **User Presence Notifications**: When users join or leave the chat, notifications are broadcast to all participants.
- **WebSocket-Based Communication**: WebSocket is used for full-duplex communication between the client and server, with SockJS as a fallback for unsupported browsers.
- **Message Types**: Users can send normal chat messages, as well as notifications about their join/leave events.

---

## Technologies Used
- **Spring Boot**: A Java-based framework used to create the backend application.
- **Spring WebSocket**: Provides support for WebSocket communication in Spring applications.
- **STOMP**: A simple text-oriented messaging protocol over WebSocket used to handle real-time message exchanges.
- **SockJS**: A JavaScript library that provides a WebSocket-like object in browsers that do not support WebSocket.
- **SLF4J**: A logging facade used to log application events such as user disconnections.

---

## Project Structure

### 1. Main Class (`ChatApplication.java`)

This is the entry point of the Spring Boot application. It is responsible for initializing the application context and starting the embedded server.

- **Function**: Starts the Spring Boot application when the `main()` method is invoked.

### 2. WebSocket Configuration (`WebSocketConfig.java`)

This configuration class sets up the WebSocket endpoints and message routing.

- **STOMP Endpoint**: Configures the STOMP endpoint `/ws` where clients will connect.
- **Message Broker**: Defines the application destination prefix `/app` for client-to-server communication and enables a simple message broker for broadcasting messages to `/topic`.

### 3. Chat Controller (`ChatController.java`)

The `ChatController` handles the sending and receiving of messages between clients.

- **Handling Messages**: The `sendMessage` method is mapped to `/chat.sendMessage`, handling the sending of chat messages. The `addUser` method is used to manage the user joining the chat and notify others of the new user.
- **User Management**: When a user joins, their username is stored in the session, allowing other users to see who is active in the chat.

### 4. Chat Message Model (`ChatMessage.java`)

This class defines the structure of a chat message. It contains fields for the sender's name, message content, and message type (such as "CHAT", "JOIN", or "LEAVE").

- **Message Builder**: The `Builder` class implements the builder pattern for easy creation of chat messages.

### 5. WebSocket Event Listener (`WebSocketEventListener.java`)

This component listens for WebSocket disconnection events.

- **User Disconnection**: When a user disconnects, it broadcasts a message to inform other users that the user has left the chat.
- **Logging**: It logs user disconnections, providing valuable information for debugging and tracking active users.

---

## How It Works

### Client-Server Communication
1. **Client Connection**: The client establishes a WebSocket connection to the `/ws` endpoint.
2. **Message Handling**: 
   - When a user sends a message, it is sent to the server via the `/app/chat.sendMessage` endpoint.
   - The server then broadcasts this message to all connected clients via the `/topic/public` endpoint.
3. **User Join/Leave**: When a user joins the chat, the server broadcasts a `JOIN` message, and similarly, when a user leaves, a `LEAVE` message is broadcast.

### WebSocket Lifecycle
- **WebSocket Session**: A WebSocket session is created when a client connects, and the session is used to manage communication between the client and the server.
- **Disconnect Handling**: When a user disconnects, a `SessionDisconnectEvent` is triggered, which is handled by the `WebSocketEventListener`. It notifies other clients that the user has left the chat.

---

## Running the Application

### Prerequisites
- **Java**: JDK 8 or higher is required to run the application.
- **Maven**: Used for building and managing dependencies.

### Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/chat-application.git
   cd chat-application
   ```
   
2. Install dependencies using Maven:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Access the application in your browser at `http://localhost:8080`.

---

## Future Enhancements
- **Authentication**: Integrate authentication mechanisms for user verification.
- **Message Persistence**: Store chat history in a database for persistent access.
- **Rich Text Messaging**: Implement support for sending rich text (e.g., bold, italics) or images.

---

T
