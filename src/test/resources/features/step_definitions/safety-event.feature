Feature: Safety Event

  Scenario: Create Safety Event
    Given an authenticated admin
    Then safety event is created

  Scenario: Get All Safety Event
    Given an authenticated admin
    Then get all safety events with line "Gestiune"