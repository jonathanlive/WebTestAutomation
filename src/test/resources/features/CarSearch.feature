@test @carSearch
Feature: CarSearch

  @test
  Scenario Outline: Search for 3 models of cars
    Given user is on the homepage
    When the user fulfill the form with brand <brand> model <model> minYear <minYear> maxYear <maxYear> minPrice <minPrice> maxPrice <maxPrice>
    And click on search button
    Then the model1 <model1> with price1 <price1> and model2 <model2> with price2 <price2> will be displayed on the page results

    Examples:
      | brand | model | minYear | maxYear  | minPrice    | maxPrice      | model1                                  | price1       | model2                                  | price2       |
      | Honda | Civic | De 2015 | Até 2021 | De R$ 4 mil | Até R$ 50 mil | Honda Civic LXS 1.8 i-VTEC (Aut) (Flex) | R$ 48.900,00 | Honda Civic LXR 2.0 i-VTEC (Aut) (Flex) | R$ 49.980,00 |

  @test
  Scenario Outline: Search cars and get first page result
    Given user is on the homepage
    When the user fulfill the form with brand <brand> model <model> minYear <minYear> maxYear <maxYear> minPrice <minPrice> maxPrice <maxPrice>
    And click on search button
    Then the page will be updated with a list of search matches

    Examples:
      | brand | model | minYear | maxYear  | minPrice    | maxPrice      |
      | Honda | Civic | De 2015 | Até 2021 | De R$ 4 mil | Até R$ 50 mil |

  @test
  Scenario Outline: Search cars and confirm the first page results
    Given user is on the homepage
    When the user fulfill the form with brand <brand> model <model> minYear <minYear> maxYear <maxYear> minPrice <minPrice> maxPrice <maxPrice>
    And click on search button
    Then the page will be updated with the last search result

    Examples:
      | brand | model | minYear | maxYear  | minPrice    | maxPrice      |
      | Honda | Civic | De 2015 | Até 2021 | De R$ 4 mil | Até R$ 50 mil |

