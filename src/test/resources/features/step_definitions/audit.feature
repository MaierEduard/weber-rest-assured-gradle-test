Feature: wkefmwkofnewokf


  Scenario: Created audit populating the externalAuthorName
    Given an authenticated admin
    Then a new audit is created and externalAuthorName is populated

  Scenario: Created audit without populating the externalAuthorName
    Given an authenticated admin
    Then a new audit is created and externalAuthorName must be Null

  Scenario: Get Audits
    Given an authenticated admin
    Then get audits with status "PASSED"


  Scenario: Get Audit by id
    Given an authenticated admin
    Then get audit by "5d492802be41760001a4ce9e"


  Scenario: Delete audit by id
    Given an authenticated admin
    Then delete audit by "5d492802be41760001a4ce9e"




#    Scenario: Create audit
#    Given an authenticated admin
#    Then a new audit

  #  Scenario: Test
#  Given an authenticated admin
#  Then Test "PASSED"