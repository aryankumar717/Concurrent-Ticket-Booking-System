# 🎟️ Concurrent Ticket Booking System (Java Swing + Multithreading)

## 📌 Project Overview
The Concurrent Ticket Booking System is a Java-based desktop application developed using Java Swing and Multithreading.  
It simulates a real-world ticket booking environment where multiple users try to book seats at the same time.  
Thread synchronization is used to ensure data consistency and to prevent overbooking.

This project is suitable for engineering-level understanding of concurrency and GUI-based Java applications.

---

## 🚀 Features
- Dynamic total seat setup
- Multiple user booking requests
- Each booking request runs as a separate thread
- Thread-safe seat allocation using synchronized methods
- Real-time booking status displayed in GUI
- Handles full, partial, and failed bookings
- Prevents race conditions

---

## 🛠️ Technologies Used
- Java (JDK 8 or higher)
- Java Swing
- Multithreading
- Synchronization
- Object-Oriented Programming (OOP)

---

## 🧩 Project Structure
TicketBookingGUI.java  
• TicketCounter – Manages shared seat resource  
• BookingRequest – Represents user booking (Thread)  
• TicketBookingGUI – GUI and thread controller  

---

## 🔄 Working Mechanism
1. Admin sets the total number of available seats.
2. Users add booking requests (name + seats).
3. Each booking request executes in a separate thread.
4. The bookTicket() method is synchronized so only one thread can access seat data at a time.
5. GUI updates are safely handled using SwingUtilities.invokeLater().

---

## 🔐 Thread Safety Explanation
- Shared Resource: availableSeats
- Critical section protected using synchronized keyword.
- Ensures:
  - No overbooking
  - Correct seat count
  - Safe concurrent execution

---

## ▶️ How to Run the Project

### Using Command Line
javac TicketBookingGUI.java  
java TicketBookingGUI  

### Using IDE (VS Code / IntelliJ / Eclipse)
1. Open project folder
2. Configure JDK
3. Run TicketBookingGUI.java

---

## 📸 Output Description
- Successful booking → ✅
- Partial booking → ⚠️
- Booking rejected when seats are full → ❌
- Remaining seats shown after each request

---

## 🎯 Learning Outcomes
- Understanding multithreading in Java
- Implementing synchronization
- Managing shared resources safely
- GUI and backend thread coordination
- Real-world concurrency simulation

---

## 🔮 Future Enhancements
- Database integration (MySQL / SQLite)
- User authentication system
- Booking cancellation feature
- Seat categories (Gold / Silver / Platinum)
- Web-based version using Spring Boot
- REST API integration

---

## 👨‍💻 Author
Aryan Kumar  
B.Tech CSE – NIET Greater Noida  

---

## 📄 License (MIT)

MIT License

Copyright (c) 2025 Aryan Kumar

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
