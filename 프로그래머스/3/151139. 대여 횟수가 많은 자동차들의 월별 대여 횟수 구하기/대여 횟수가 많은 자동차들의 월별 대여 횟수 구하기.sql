WITH month_data AS ( 
SELECT car_id, COUNT(car_id)
FROM car_rental_company_rental_history 
WHERE DATE(start_date) BETWEEN '2022-08-01' AND '2022-10-31'
GROUP BY car_id
HAVING COUNT(car_id) >= 5
)

SELECT MONTH(start_date) AS MONTH, car_id AS CAR_ID, COUNT(car_id) AS RECORDS
FROM car_rental_company_rental_history 
WHERE DATE(start_date) BETWEEN '2022-08-01' AND '2022-10-31'
AND car_id IN (SELECT car_id FROM month_data)
GROUP BY MONTH(start_date), car_id
ORDER BY MONTH, car_id DESC