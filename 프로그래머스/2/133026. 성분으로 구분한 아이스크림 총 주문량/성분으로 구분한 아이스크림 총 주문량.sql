SELECT INGREDIENT_TYPE, SUM(fh.total_order) AS TOTAL_ORDER
FROM first_half AS fh LEFT JOIN icecream_info AS info
ON fh.flavor = info.flavor
GROUP BY ingredient_type