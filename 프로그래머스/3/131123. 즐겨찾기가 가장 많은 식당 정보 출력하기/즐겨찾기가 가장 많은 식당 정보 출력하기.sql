SELECT FOOD_TYPE, REST_ID, REST_NAME, FAVORITES
FROM rest_info
WHERE (food_type, favorites) IN (SELECT food_type, MAX(FAVORITES)
                              FROM rest_info
                              GROUP BY food_type)
ORDER BY food_type DESC