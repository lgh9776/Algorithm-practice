SELECT category AS CATEGORY, SUM(book_sales.sales) AS TOTAL_SALES
FROM book JOIN book_sales
ON book.book_id = book_sales.book_id
WHERE book_sales.sales_date BETWEEN '2022-01-01' AND '2022-01-31'
GROUP BY category
ORDER BY category