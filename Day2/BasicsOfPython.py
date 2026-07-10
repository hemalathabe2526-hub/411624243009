List = [1, 2, 3.15, 7, 9, "Hello", "World", 5.19, True, False]
List.append("Python")
List.pop(3)
print(List)

Set = set()
Set.add(2)
Set.add(1)
print(Set)

Dictonary = {"Name": "John", "Age": 30, "City": "New York"}
for key, value in Dictonary.items():
    print(key, ":", value)

def my_function():
    return "Hello from my_function!"
print(my_function())

class MyClass:
    def __init__(self, name):
        self.name = name
    def my_function(self):
        print("Hello from", self.name)
    def my_function2(self, age):
        print("Hello from", self.name, "who is", age, "years old")
object = MyClass("Shalini")
object.my_function()
object.my_function2(25)

Dictionary2 = {"Name" : "Alice", "Age" : 28, "Salary" : 50000}
import json
json_string = json.dumps(Dictionary2)
print(json_string)
print(type(json_string))

with open("User.json", "r") as file:
    data = json.load(file)
    print(data)
    print(type(data))

# After This we have created the Virtual Environment and installed the required packages in it.
# By using the command of -> python -m venv BasicsOfPythonenv <- this will create the virtual environment in the current directory.
# To activate the virtual environment,  we can use the command of source -> BasicsOfPythonenv/Scripts/activate <- in the terminal.
# To deactivate the virtual environment, we can use the command of -> deactivate <- in the terminal.