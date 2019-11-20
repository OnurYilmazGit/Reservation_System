select name,city,stars from business where id in 
(
select business_id from review where user_id in 
( 
select id from user 
group by(id)
order by(useful_votes) desc
)

)
ORDER BY(stars) desc
LIMIT 5;
