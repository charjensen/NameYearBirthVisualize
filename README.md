# Name Data Analysis Tool

Demo:

[![Name Data Analysis Tool](https://img.youtube.com/vi/k9C_lOLRu20/0.jpg)](https://www.youtube.com/watch?v=k9C_lOLRu20)

This Java program allows users to analyze name data over the years or get the most popular names for a specific year. The program uses the `DUDraw` library for drawing graphs and handling user interactions.

## Features

- **Name Data Over Years**: Displays the popularity of a given name over the years.
- **Year Data for Names**: Displays the most popular names for a given year.

## Usage

### Choose Mode

- Enter `1` for name data over years.
- Enter `2` for year data for names.

### Name Data Over Years

1. Enter a name and gender (e.g., Mary, F).
2. The program will display a graph showing the popularity of the name over the years.
3. You can choose to enter another name or exit the program.

### Year Data for Names

1. Enter a year.
2. Enter the number of names you would like to see.
3. The program will display the most popular names for the given year.

## Code Overview

### Main Class: `Driver`

- **Main Method**: Handles user input and determines the mode of operation.
- **Instance Variables**: 
  - `totalNames`: Array to store the total names per year.
  - `yearData`: HashMap to store year data.
  - `nameData`: HashMap to store name data.
  - `keyboard`: Scanner for user input.

### Dependencies

- **DUDraw**: Library for drawing and handling user interactions.
