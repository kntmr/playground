factorial :: Int -> Int
factorial 0 = 1
factorial n = n * factorial (n - 1)

showNumber :: Int -> String
showNumber 1 = "One"
showNumber 2 = "Two"
showNumber 3 = "Three"

addVectors :: (Int, Int) -> (Int, Int) -> (Int, Int)
addVectors (x1, y1) (x2, y2) = (x1 + x2, y1 + y2)

sumThree :: (Num a) => [a] -> a
sumThree [] = 0
sumThree (x:[]) = x
sumThree (x:y:[]) = x + y
sumThree (x:y:z:[]) = x + y + z
sumThree (x:y:z:_) = x + y + z

firstLetter :: String -> String
firstLetter "" = "Emptry string."
firstLetter all@(x:xs) = "The first letter of " ++ all ++ " is " ++ [x]
