Feature: Groups

  Scenario: Group creation
    Given a set of groups
    When I create a new group with name xxx, header yyy, footer zzz
    Then the new set of groups is equal to the old set with th added group
