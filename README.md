# 🎟️ Concurrent Ticket Booking System  
**Java Swing • Multithreading • Concurrency Control**

---

## 📌 Project Overview
The **Concurrent Ticket Booking System** is a Java-based desktop application developed using **Java Swing** and **multithreading concepts**.  
It simulates a real-world ticket booking scenario where **multiple users attempt to book seats concurrently**, while ensuring **data consistency, fairness, and thread safety**.

This project is designed at an **engineering (B.Tech CSE) academic level** and demonstrates core concepts of concurrency and GUI-based application development in Java.

---

## 🚀 Key Features
- Dynamic total seat configuration  
- Multiple concurrent booking requests  
- Booking handled using a **thread pool (ExecutorService)**  
- Thread-safe seat allocation using **ReentrantLock**  
- Handles:
  - ✅ Successful bookings  
  - ⚠️ Partial bookings  
  - ❌ Failed bookings  
- Live remaining seats display  
- Persistent booking logs (`booking_log.txt`)  
- Final booking summary after execution  
- Prevents race conditions and overbooking  

---

## 🛠️ Technologies & Concepts Used
- Java (JDK 8 or higher)  
- Java Swing (GUI)  
- Multithreading & Concurrency  
- ExecutorService (Thread Pool)  
- ReentrantLock (Fair Locking)  
- CountDownLatch (Thread Coordination)  
- File Handling  
- Object-Oriented Programming (OOP)  

---

## 🧩 Project Structure

TicketBookingGUI.java
│
├── TicketCounter
│   • Manages shared seat resource
│   • Uses ReentrantLock for thread safety
│
├── BookingRequest
│   • Implements Runnable
│   • Represents a booking task
│
└── TicketBookingGUI
• Swing-based user interface
• Thread pool and execution controller

---

## 🔄 Working Mechanism
1. Admin sets the total number of seats.  
2. Users add booking requests (user name and seat count).  
3. Each request is submitted to a **fixed-size thread pool**.  
4. The `bookTicket()` method executes inside a **ReentrantLock-protected critical section**.  
5. Seats are allocated safely without race conditions.  
6. GUI updates are handled using `SwingUtilities.invokeLater()`.  
7. After all threads complete, a **booking summary** is displayed.  

---

## 🔐 Thread Safety & Concurrency Design
- **Shared Resource:** `availableSeats`  
- **Locking Mechanism:** `ReentrantLock (fair = true)`  
- **Thread Coordination:** `CountDownLatch`  
- **Execution Model:** ExecutorService  

This design ensures:
- No overbooking  
- Accurate seat count  
- Safe concurrent execution  

---

## 📄 Logging & Persistence
All booking transactions are written to a file named:

booking_log.txt

This log helps in debugging, verification, and understanding concurrent execution behavior.

---

## ▶️ How to Run the Project

### Using Command Line

javac TicketBookingGUI.java
java TicketBookingGUI

### Using IDE (VS Code / IntelliJ / Eclipse)
1. Open the project folder  
2. Configure JDK (8 or higher)  
3. Run `TicketBookingGUI.java`  

---

## 📸 Output Description
- ✅ Successful booking → seats allocated  
- ⚠️ Partial booking → limited seats allocated  
- ❌ Failed booking → no seats available  
- 📊 Final booking summary displayed after execution  

---

## 🎯 Learning Outcomes
- Practical understanding of Java multithreading  
- Using thread pools instead of manual thread creation  
- Implementing fair locking with ReentrantLock  
- Managing shared resources safely  
- Coordinating GUI operations with background threads  

---

## 🔮 Future Enhancements
- Database integration (MySQL / SQLite)  
- Booking cancellation feature  
- Seat categories (Gold / Silver / Platinum)  
- User authentication system  
- Web-based version using Spring Boot  
- REST API integration  

---

## 👨‍💻 Author
**Aryan Kumar**  
B.Tech CSE  
NIET, Greater Noida  

---

## 📄 License (MIT)

MIT License  

Copyright (c) 2025 Aryan Kumar  

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND.
