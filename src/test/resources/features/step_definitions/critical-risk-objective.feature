Feature: Critical Risk Objective Controller

  Scenario: Create Critical Risk
    Given an authenticated admin
    Then critical risk is created

  Scenario: Get All Critical Risk
    Given an authenticated admin
    Then get all critical risk

  Scenario: Get Critical Risk By Id
    Given an authenticated admin
    Then get critical risk by "5cb582debe41760001a4cb71"

  Scenario: Delete Critical Risk By Id
    Given an authenticated admin
    Then delete critical risk by "5d6e5951be41760001a4d071"