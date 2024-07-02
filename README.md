# KaanBooking

KaanBooking is a desktop application developed to manage hotel reservations. This project is built using Java Swing and integrated with PostgreSQL database.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Architecture](#architecture)
- [Database Structure](#database-structure)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)

## Features

- User-friendly interface for creating, updating, and deleting reservations
- Saving customer details (name, TC identity number, email, phone number)
- Creating reservations with specified number of children and adults
- Calculating total price based on selected dates and room prices
- Selecting start and finish dates for reservations
- Ensuring data persistence using PostgreSQL database

## Technologies

- **Java**: Primary programming language of the application
- **Swing**: GUI library used for building the graphical user interface
- **PostgreSQL**: Database management system for storing application data
- **Maven**: Project management and build tool

## Architecture

This project follows the MVC (Model-View-Controller) architectural pattern:

- **Model**: Classes under `entity` package. Examples: `Booking`, `Room`, etc.
- **View**: Swing components under `view` package. Examples: `BookingView`, `RoomView`, etc.
- **Controller**: Business logic classes under `business` package. Examples: `BookingManager`, `RoomManager`, etc.

## Database Structure

The database consists of two main tables: `booking` and `room`.

### `booking` Table

| Column         | Data Type         | Properties                |
|----------------|-------------------|---------------------------|
| booking_id     | SERIAL            | PRIMARY KEY               |
| room_id        | INTEGER           | FOREIGN KEY (room_id)     |
| customer_name  | VARCHAR(255)      | NOT NULL                  |
| customer_tc    | VARCHAR(20)       | NOT NULL                  |
| customer_mail  | VARCHAR(255)      | NOT NULL                  |
| customer_phone | VARCHAR(20)       | NOT NULL                  |
| start_date     | DATE              | NOT NULL                  |
| finish_date    | DATE              | NOT NULL                  |
| child_number   | INTEGER           | NOT NULL                  |
| adult_number   | INTEGER           | NOT NULL                  |
| total_price    | DOUBLE PRECISION  | NOT NULL                  |
| customer_note  | TEXT              |                           |

### `room` Table

| Column      | Data Type        | Properties                |
|-------------|------------------|---------------------------|
| room_id     | SERIAL           | PRIMARY KEY               |
| room_name   | VARCHAR(255)     | NOT NULL                  |
| room_price  | DOUBLE PRECISION | NOT NULL                  |

## Installation

1. **Clone the Repository:**
    ```bash
    git clone https://github.com/kaanacikgoz/KaanBooking.git
    cd KaanBooking
    ```

2. **Install Dependencies:**
    ```bash
    mvn clean install
    ```

3. **Set Up the Database:**
    - Create your PostgreSQL database and run the `src/main/resources/schema.sql` file to create necessary tables.
    - Update database connection settings in `src/main/resources/application.properties`.

## Usage

1. **Run the Application:**
    - Open the project in your IDE and run the main class `Main.java`.

2. **Making a Reservation:**
    - Enter necessary customer details, select dates, specify child and adult numbers, and save the reservation through the application interface.

## Contributing

Contributions are welcome! To contribute to this project, follow these steps:

1. Fork this repository.
2. Create a new branch: `git checkout -b feature/your-feature-name`.
3. Commit your changes: `git commit -m 'Add a new feature'`.
4. Push to the branch: `git push origin feature/your-feature-name`.
5. Submit a pull request.

