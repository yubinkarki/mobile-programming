//
//  ViewController.swift
//  mobile-programming-ios
//
//  Created by Yubin on 5/3/25.
//

import UIKit

class ViewController: UIViewController {
    // Hold the control button and drag the widget here
    @IBOutlet var resultLabel: UILabel!
    @IBOutlet var firstNumField: UITextField!
    @IBOutlet var secondNumField: UITextField!

    @IBAction func addButton(_ sender: Any) {
        var result: Double = 0;
        let firstValue = Double(firstNumField.text!) ?? 0.0
        let secondValue = Double(secondNumField.text!) ?? 0.0

        result = firstValue + secondValue

        let formattedResult = String(format: "%.2f", arguments: [result])

        resultLabel.text = "\(formattedResult)"
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        // Create a tap gesture recognizer
        let tapGesture = UITapGestureRecognizer(target: self, action: #selector(dismissKeyboard))

        // Make it not interfere with other controls
        tapGesture.cancelsTouchesInView = false

        view.addGestureRecognizer(tapGesture)
    }

    @objc func dismissKeyboard() {
        // Resign first responder status from any first responder (typically the active keyboard)
        view.endEditing(true)
       }
}
