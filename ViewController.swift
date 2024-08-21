import UIKit

class ViewController: UIViewController {
    @IBOutlet var resultLabel: UILabel!
    @IBOutlet var firstNumField: UITextField!
    @IBOutlet var secondNumField: UITextField!

    @IBAction func addButton(_ sender: Any) {
        var result: Double = 0;
        let firstValue = Double(firstNumField.text!)
        let secondValue = Double(secondNumField.text!)

        result = firstValue! + secondValue!

        let formattedResult = String(format: "%.2f", arguments: [result])

        resultLabel.text = "\(formattedResult)"
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
}
