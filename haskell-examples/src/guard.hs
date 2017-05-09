fact :: Integer -> Integer
fact n
    | n < 0 = error "error!!"
    | n == 0 = 1
    | otherwise = n * fact(n - 1)

totalCheck :: Integer -> Integer -> String
totalCheck x y
    | x + y < 10 = "less than 10"
    | x + y < 100 = "less than 100"
    | otherwise = "a large number"

totalCheck' :: Integer -> Integer -> String
totalCheck' x y
    | total < 10 = "less than 10"
    | total < 100 = "less than 100"
    | otherwise = "a large number"
    where total = x + y

initials :: String -> String -> String
initials firstname lastname = [f] ++ ". " ++ [l] ++ "."
    where (f:_) = firstname
          (l:_) = lastname

calcCircleArea :: [Double] -> [Double]
calcCircleArea xs = [area | r <- xs, let area = r * r * 3.14]

head' :: [a] -> a
head' [] = error "empty list"
head' (x:_) = x

head'' :: [a] -> a
head'' xs = case xs of [] -> error "empty list"
                       (x:_) -> x

checkList :: [a] -> String
checkList list = "This list is "
               ++ case list of [] -> "empty."
                               [x] -> "a singleton list."
                               xs -> "a longer list."

checkList' :: [a] -> String
checkList' list = "This list is " ++ what list
    where what [] = "empty."
          what [x] = "a singleton list."
          what xs = "a longer list."

