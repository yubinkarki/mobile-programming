//
//  main.swift
//  mobile-programming-unit-eight
//
//  Created by Yubin on 14/08/2024.

print("Namaste sabai jana lai 🙏🏼\n")

// Data types
var name: String?
var emtpyString: String = ""
var floatNumber: Float = 3.14

// Constant data
let gender: String = "Male"

// String interpolation
print("Floating number is \(floatNumber) and gender is \(gender)\n")

// Conditional statement
if name != nil {
    print(name ?? "default")
} else {
    print("Name not found\n")
}

// Creating function (with parameters)
func sumOfArray(numbers: [Int]) -> Int {
    var sum = 0
    for num in numbers {
        sum += num
    }
    return sum
}

var numberList: [Int] = [1, 1, 1, 1, 1]
var sumResult: Int = sumOfArray(numbers: numberList)
print("The final sum is >>", sumResult)

// Range operator
for number in 1 ... 5 {
    print("Range operator >>", number)
}

func greatestOfThree(a: Int, b: Int, c: Int) -> Int {
    return (a >= b && a >= c) ? a : (b >= c ? b : c)
}

print("\nThe greatest number is", greatestOfThree(a: 10, b: 20, c: 30))
