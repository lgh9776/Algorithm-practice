SELECT DISTINCT(car_id) AS CAR_ID
FROM car_rental_company_rental_history
WHERE car_id IN (
    SELECT car_id
    FROM car_rental_company_car
    where car_type LIKE '세단'
)
AND start_date >= '2022-10-01'
AND start_date <= '2022-10-31'
ORDER BY car_id DESC