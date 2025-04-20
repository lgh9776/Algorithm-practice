SELECT ANIMAL_ID, ANIMAL_TYPE, NAME
FROM animal_outs
WHERE (sex_upon_outcome LIKE "Spayed Female"
OR sex_upon_outcome LIKE "Neutered Male")
AND animal_id IN (SELECT animal_id 
                  FROM animal_ins 
                  WHERE sex_upon_intake LIKE "Intact%")