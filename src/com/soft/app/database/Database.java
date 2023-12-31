package com.soft.app.database;

import java.util.UUID;

import com.soft.app.beans.Booking;
import com.soft.app.beans.Car;
import com.soft.app.beans.Customer;
import com.soft.app.util.CarTypes;

public class Database {

  private Customer[] customers = new Customer[5];
  private Car[] cars = new Car[4];
  private Booking[] bookings = new Booking[5];
  private static int trackIndex = 0;
  private static int trackBookingIndex = 0;

  public Database() {
    populateCars();
  }

  public Customer saveCustomer(Customer customer) {
    //implementation
    if (trackIndex < customers.length) {
      customers[trackIndex] = customer;
      trackIndex ++;
      return customer;
    }

    System.out.println("We ran out of space to save Customer!");
    return null;
    
  }
  //save to booking DB
  public Booking saveBooking(Booking booking) {
    //implementation
    if (trackBookingIndex  < bookings.length) {
      bookings[trackBookingIndex] = booking;
      trackBookingIndex ++;
      return booking;
    }

    System.out.println("We ran out of space to save Customer!");
    return null;
    
  }
  //fetch customer using customer code
  public Customer fetchCustomer(String customerCode) {
    for (Customer cst : getCustomers()) {
      if (cst != null) {
        if (cst.getCustomerCode().toUpperCase().trim().equals(customerCode.toUpperCase().trim())) {
          return cst;
        }
      }
    }
    return null;
  }

  //fetch All Customers 
  public void fetchAllCustomer() {
    int count = 0;
    for (Customer customer : getCustomers()) {
      if (customer != null) {
        ++count;
        System.out.println();
        System.out.println("# CUSTOMER " + ++count);
        System.out.println(customer);
      }
    }
    if(count == 0){
        System.out.println("\nNo Record Found");
    }
  }

  //fetch car by id
  public Car fetchCarById(String id) {
    for(Car car : getCars()){
      if (car.getId().equals(UUID.fromString(id))) {
        return car;
      }
    }
    return null;
  }

  //fetch all bookings
  public void fetchAllBookings(){
    int count = 0;
    for (Booking booking : getBookings()) {
      if (booking != null) {
        System.out.println();
        System.out.println("# BOOKING " + ++count);
        System.out.println(booking);
      }  
    }
    if(count == 0){
        System.out.println("\nNo Available Bookings");
    }
  }

  //fetch all user booked cars
  public void allCustomerBookedCars(Customer customer){
    int count = 0;
    for (Booking booking : getBookings()) {
      if (booking != null && booking.getCustomer().getCustomerCode().equals(customer.getCustomerCode())) {
        System.out.println();
        System.out.println("USER BOOKED CARS #" + ++count);
        System.out.println(
          "\nCustomer: " + booking.getCustomer().getName()+
          "\nCar: " + booking.getCar()
        );
      }
      
    }
    if(count == 0){
        System.out.println("\nNo record available");
    }
  }
  //set car status to 1 when car he chosen by the user
  public boolean setCarStatus(Car car, int status){
    for (Car c : getCars()) {
      if (car.getId().equals(c.getId())) {
        c.setStatus(status);
        return true;
      }
    }
    return false;
  }

  //fetch all avaialable cars
  public void allAvaiableCars(){
    int count = 0;
    for (Car car : getCars()) {
      if (car != null && car.getStatus() == 0) {
        System.out.println();
        System.out.println("# CAR " + ++count);
        System.out.println(
          "\nBrand: " + car.getBrand()+
          "\nType: " + car.getType()+
          "\nNumber Plate: " + car.getNumberPlate()+
          "\nSeats: " + car.getNoOfSeats()
        );
      }
    }
    if(count == 0){
        System.out.println("No Avaiable Cars");
    }
  }
  //fetch Available Electric Cars
  public void allAvaiableElectricCars(){
    int count = 0;
    for (Car car : getCars()) {
      if (car != null && car.getStatus() == 0 && car.getType() == CarTypes.ELECTRIC) {
        System.out.println();
        System.out.println("# CAR" + ++count);
        System.out.println(
          "\nBrand: " + car.getBrand()+
          "\nType: " + car.getType()+
          "\nNumber Plate: " + car.getNumberPlate()+
          "\nSeats: " + car.getNoOfSeats()
        );
      }
    }
    if(count == 0){
        System.out.println("\nNo Avaiable Electric Cars");
    }
  }
  //fetch Customers and booked Cars

  /**
   * Populate car array
   */
  public void populateCars() {
    cars[0] = new Car("Toyota", CarTypes.ELECTRIC, "123456", 3, 0);
    cars[1] = new Car("BMW", CarTypes.PETROL, "654321", 4, 0);
    cars[2] = new Car("Tesla", CarTypes.ELECTRIC, "258416", 2, 0);
    cars[3] = new Car("Honda", CarTypes.PETROL, "455320", 4, 1);
  }

  //returns customers
  public Customer[] getCustomers() {
    return customers;
  }

  //return Cars
  public Car[] getCars() {
    return cars;
  }
  //get bookings
  public Booking[] getBookings() {
    return bookings;
  }
 
}
