SELECT mcdp_cd as '진료과코드', count(mddr_id) AS '5월예약건수'
FROM appointment
WHERE apnt_ymd BETWEEN '2022-05-01' AND '2022-05-31'
GROUP BY mcdp_cd
ORDER BY count(mddr_id), mcdp_cd