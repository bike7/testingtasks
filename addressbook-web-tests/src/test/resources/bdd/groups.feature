Feature: Groups

  Scenario Outline: Group creation
    Given a set of groups
    When I create a new group with name: <name>, header: <header>, footer: <footer>
    Then the new set of groups is equal to the old set with th added group

    Examples:
      | name      | header      | footer      |
      | test name | test header | test footer |
      | xxx       | yyy         | zzz         |