
################### PRINT EXAMPLE USING FORMAT STRINGS##################################
# change this code
mystring = 'hello'
myfloat = 10.0
myint = 20 

# testing code
if mystring == "hello":
    print ("String: %s" % mystring);
if isinstance(myfloat, float) and myfloat == 10.0:  # notice colon : 
    print ("Float: %f" % myfloat) ;
if isinstance(myint, int) and myint == 20:
    print ("Integer: %d" % myint) ;
	
############################## SIMPLE LIST EXAMPLES	###################
mylist = []
mylist.append(1)
mylist.append(2)
mylist.append(3)
print(mylist[0]) # prints 1
print(mylist[1]) # prints 2
print(mylist[2]) # prints 3

# prints out 1,2,3
# indentation is necessary in loops else python gives error
for x in mylist: # notice colon
    print (x)
	
	
############################################## LIST AND LOOP & INPUT EXAMPLE#################################
###############################MULTILINE COMMENT IS IMPLEMENTED BY THREE QUOTES ''''#####################################	
################# LOOP IMPLEMENTED BY INDENT###########
numbers = []
strings = []
names = ["John", "Eric", "Jessica"]

# write your code here
numbers.append(1)
numbers.append(2)
numbers.append(3)

strings.append("hello")
strings.append("world")

second_name = names[1]
names.append(names[2])


##### this code should write out the filled arrays and the second name in the names list (Eric).
print(numbers)
print(names[3])
print("The second name on the names list is %s" % second_name)

####### Watch for Error
''' for x in names:
 print(names[x])  Traceback (most recent call last):
  File "python", line 15, in <module>
TypeError: list indices must be integers, not str '''
###### MULTILINE COMMENT ENDED HERE


for x in numbers:
    print(x)
    print(names[x]) 

for x in range(1, len(names)): ################## notice the colon
 print(names[x])  ############## NOTICE INDENT
################### Can use input() to wait for User to Type something in new line 

for x in range(1,int(input("type the number 3"))):    ###### by default input is string had to cast as int
    print(names[x])
	

	
################################## String Concat & Multiplication
lotsofhellos = ("hello" +" ")* 10
print(lotsofhellos)

#################################### Operation with List
even_numbers = [2,4,6,8]
odd_numbers = [1,3,5,7]
all_numbers = odd_numbers + even_numbers #### Join/Concats List
print(all_numbers)
sorted=all_numbers.sort()  ######## Doesn't return anything
print(sorted)
######## Proper way to sort
all_numbers.sort()
print(all_numbers)	
##################OR IN Python 2
print sorted(all_numbers)


'''
The target of this exercise is to create two lists called x_list and y_list, which contain 10 instances of the variables x and y, respectively. You are also required to create a list called big_list, which contains the variables x and y, 10 times each, 
by concatenating the two lists you have created.'''


x = object()
y = object()

# TODO: change this code
x_list = [x]*10
y_list = [y]*10
big_list = x_list+y_list

print ("x_list contains %d objects" % len(x_list))
print ("y_list contains %d objects" % len(y_list))
print ("big_list contains %d objects" % len(big_list))

# testing code
if x_list.count(x) == 10 and y_list.count(y) == 10:
    print ("Almost there...")
if big_list.count(x) == 10 and big_list.count(y) == 10: ### Notice colon
    print ("Great!"  )
    
######### I can count    
numbers = [1,2,1,3,1,4,1,5]
print("number of times 1 present is %d" %numbers.count(1))

lotsofhellos = ("hello" +" ")* 10
### It supported  Different Format of Print
print("number of time hello is written is", lotsofhellos.count("hello")) 
print(numbers,lotsofhellos)



### String Formatting

# This prints out "Hello, John!"
name = "John"
print ("Hello, %s!" %name)

##To use two or more argument specifiers, use a tuple (parentheses):
# This prints out "John is 23 years old."
name = "John"
age = 23
print ("%s is %d years old." % (name, age))
# OR
print(name,"is",age,"years old.")

#######This prints out: A list: [1, 2, 3]
mylist = [1,2,3]
print(mylist)
print ("A list: %s" % mylist)
print("%s"%mylist)

'''
%s - String (or any object with a string representation, like numbers)
%d - Integers
%f - Floating point numbers
%.<number of digits>f - Floating point numbers with a fixed amount of digits to the right of the dot.
%x/%X - Integers in hex representation (lowercase/uppercase) '''

print(12.33)
print("%.2f" %12.33)
print("%.5f" %12.33)

data = ("John", "Doe", 53.44) ##### This is not list but Tuple
format_string = "%s%s%s"
print ("%s%s%s" %data)
print(data)
print(format_string,data)

################## Basic String Operations


astring = "Hello world!"
print (len(astring))
print (astring.index("o"))
print (astring.count("l"))
print (astring[6:11])
print (astring[0])
print (astring[:5])
print (astring.upper())
print (astring.lower())
print (astring.startswith("Hello"))
print (astring.endswith("asdfasdfasdf"))

if astring.startswith("Hello"):
    print("True Hai")
    print("False Hai")
    
afewwords = astring.split(" ") #####Important Functions
print(afewwords)



s = "Hey there! what should this string be?"
# Length should be 20
print ("Length of s = %d" % len(s))

# First occurrence of "a" should be at index 8
print ("The first occurrence of the letter a = %d" % s.index("a"))

# Number of a's should be 2
print ("a occurs %d times" % s.count("a"))

# Slicing the string into bits
print ("The first five characters are '%s'" % s[:5] )# Start to 5
print ("The next five characters are '%s'" % s[5:10] )# 5 to 10
print ("The twelfth character is '%s'" % s[12]) # Just number 12

print ("The last five characters are '%s'" % s[-5:]) # 5th-from-last to end

# Convert everything to uppercase
print ("String in uppercase: %s" % s.upper())

# Convert everything to lowercase
print ("String in lowercase: %s" % s.lower())

# Check how a string starts
if s.startswith("Hey"):
    print ("String starts with 'Hey'. Good!")

# Check how a string ends
if s.endswith("be?"):
    print ("String ends with 'be?'. Good!")

# Split the string into three separate strings,
# each containing only a word
print ("Split the words of the string: %s" % s.split(" "))




###################################Conditional 

x = 2
print (x == 2) # prints out True
print (x == 3) # prints out False
print (x < 3)  # prints out True


name = "John"
age = 23
if name == "John" and age == 23:
    print ("Your name is John, and you are also 23 years old.")

if (name == "John" or name == "Rick"):
    print ("Your name is either John or Rick.")
    
if name in ["John", "Rick"]:
    print ("Your name is either John or Rick.")


'''Python uses indentation to define code blocks, instead of brackets. The standard Python indentation is 4 spaces, although tabs and any other space size will work, as long as it is consistent. Notice that code blocks do not need any termination.

Here is an example for using Python's "if" statement using code blocks: 

if <statement is true>:
    <do something>
    ....
    ....
elif <another statement is true>: # else if
    <do something else>
    ....
    ....
else:
    <do another thing>
    ....
    ....
'''


x = 2
if x == 2:
    print ("x equals two!")
elif x<3:
    print("x less than three")
else:
    print ("x does not equal to two.")


x=1
if x:
    print("non empty or non zero means true")
else: 
    print("false")
  
########################### Let's try comparing lists    
x=[1,2]
if x > [1,4]:
    print("comparing lists works") ## It's only looking at first value
else:
    print("whatever")
    
if x==[1,1]:
    print("== compares whole list")
elif x==[1,2]:
    print("this should work")
    
    
##########################################operator "==", the "is" operator does not match the values of the variables, but the instances themselves
x = [1,2,3]
y = [1,2,3]
print (x == y )# Prints out True
print (x is y) # Prints out False


######################### Not Operator
print (not False) # Prints out True
print ((not False) == (False)) # Prints out False
    
################################################ Conditional Operator
# change this code
number = 10
second_number = 10
first_array = [1]
second_array = [1,2,3]

if number < 15:
    print ("1")

if first_array:
    print ("2")

if len(second_array) > 1:
    print ("3")

if len(first_array) + len(second_array) == 4:
    print ("4")

if first_array and first_array[0] == 1:
    print ("5")

if not second_number <1 :
    print ("6")
	
	
########################################################################## Loop
primes = [2, 3, 5, 7]
for prime in primes:
    print (prime)
for i in range(0,len(primes)):
    print(primes[i])
    
'''For loops can iterate over a sequence of numbers using the "range" and "xrange" functions. The difference between range and xrange is that the range function returns a new list with numbers of that specified range, whereas xrange returns an iterator, which is more efficient. (Python 3 uses the range function, which acts like xrange). Note that the xrange function is zero based.'''

# Prints out the numbers 0,1,2,3,4
for x in range(5): # or range(5)
    print (x)

# Prints out 3,4,5
for x in range(3, 6): # or range(3, 6)
    print (x)

# Prints out 3,5,7
for x in range(3, 8, 2): # or range(3, 8, 2) #  range(start, stop[, step])
    print (x)

##"while" loops

##While loops repeat as long as a certain boolean condition is met. For example:

# Prints out 0,1,2,3,4

count = 0
while count < 5:
    print (count)
    count += 1  # This is the same as count = count + 1
    
##"break" and "continue" statements

##break is used to exit a for loop or a while loop, whereas continue is used to skip the current block, and return to the "for" or "while" statement. 
# Prints out 0,1,2,3,4

count = 0
while True:
    print (count)
    count += 1
    if count >= 5:
        break

# Prints out only odd numbers - 1,3,5,7,9
for x in range(10):
    # Check if x is even
    if x % 2 == 0:
        continue
    print (x)    

##################################################################Functions
# Function returns a list of strings
def list_benefits():
    return "More organized code", "More readable code", "Easier code reuse", "Allowing programmers to share and connect code together"

def list_numbers():
    return 0,1,2,3,4

# Accepts any input and prints returns a input for print
def build_sentence(x):
    return "%s is a benefit of functions!" % x


def name_the_benefits_of_functions(): 
    list_of_benefits = list_benefits()
    for benefit in list_of_benefits:
        print (build_sentence(benefit))
        
name_the_benefits_of_functions()

n_list =list_numbers()
print(n_list)
print(n_list[1])

###########################################################################Classes & Objects
# define the Vehicle class
class Vehicle:
    name = ""
    kind = "car"
    color = ""
    value = 100.00
    def description(self):
        desc_str = "%s is a %s %s worth $%.2f." % (self.name, self.color, self.kind, self.value)
        return desc_str

# your code goes here
car1 = Vehicle()
car1.name = "Fer"
car1.color = "red"
car1.kind = "convertible"
car1.value = 60000.00

car2 = Vehicle()
car2.name = "Jump"
car2.color = "blue"
car2.kind = "van"
car2.value = 10000.00

# test code
print (car1.description())
print (car2.description())

########################################################################### Dictionaries
'''
Originally, Python items() built a real list of tuples and returned that. That could potentially take a lot of extra memory.

Then, generators were introduced to the language in general, and that method was reimplemented as a iterator-generator method named iteritems(). The original remains for backwards compatibility.

One of Python 3’s changes is that items() now return iterators, and a list is never fully built. The iteritems() method is also gone, since items() now works like iteritems() in Python 2.  '''


phonebook = {}
phonebook["John"] = 938477566
phonebook["Jack"] = 938377264
phonebook["Jill"] = 947662781

phonebook = {
    "John" : 938477566,
    "Jack" : 938377264,
    "Jill" : 947662781
}


phonebook["Jake"] = 938273443
del phonebook["Jill"]

print(phonebook["John"])

phonebook.pop("John")

if "Jake" in phonebook:
    print ("Jake is listed in the phonebook.")
if "Jill" not in phonebook:
    print ("Jill is not listed in the phonebook.")
    
    
print(phonebook.items())

for name, number in phonebook.items():
    print ("Phone number of %s is %d" % (name, number))    
    
################################################################### Modules & Packagess 

import re
# Your code goes here
member_list = []
print (dir(re))
print("\n")
for member in dir(re):
    if "find" in member:
        member_list.append(member)

print sorted(member_list) ############################# In Python 2

number_list =[1,5,2,6,3]
print sorted (number_list)

#### OR

number_list.sort()
print(number_list)

############################################################################ Generator	

import random

def lottery():
    # returns 6 numbers between 1 and 40
    for i in range(6):
        yield random.randint(1, 40)


    # returns a 7th number between 1 and 15
    yield random.randint(1,15)
    
    
for random_number in lottery():
    print ("And the next number is... %d!" % random_number)
    

def yield_2():
    for i in range (1,5):
        yield 2 

for x in  yield_2():
    print(x)        

x= yield_2()
print(x)



# fill in this function
def fib():
    a, b = 1, 1
    while 1:
        yield a
        a, b = b, a + b
        

# testing code
import types
if type(fib()) == types.GeneratorType:
    print ("Good, The fib function is a generator.\n")
    print(" The Fibonacci Series \n")

    counter = 0
    for n in fib():
        print (n)
        counter += 1
        if counter == 10:
            break

def double_generator(x):
    while 1:
        yield x
        x += x

def table_generator(x):
    y = x
    while 1:
        yield y
        y += x
        
counter=0
print("Exp: Doubling Series")
for n in double_generator(1):
    print(n)
    counter += 1
    if counter == 10:
        counter=0 ## reset counter for loop written below:)
        break
 
print("Generating Table \n ")
for n in table_generator(2):
    print(n)
    counter += 1
    if counter == 10:
        couner=0 ## reset counter for loop written below :)
        break
    
def power_generator(x):
    y=x
    while 1:
        yield y
        y *= x
counter = 0
print("Generating Power Series upto 4 Steps \n")
for n in power_generator(2):
    print(n)
    counter += 1
    if counter == 4:
        counter = 0
        break

		
############Iterators###################
print(",".join(["a", "b", "c"]))
print (",".join({"x": 1, "y": 2}))
print(list("python"))
print(list({"x": 1, "y": 2}))

##The built-in function iter takes an iterable object and returns an iterator.

number_list =[10,20,30,40,50,60]
for y in number_list:
    print(y)
    
x= iter(number_list)
print(x)
print(next(x))
print(next(x))
print(next(x))
print(next(x))
print(next(x))
print(next(x))

###############Iterators are implemented as classes. Here is an iterator that works like built-in xrange function.
'''
The __iter__ method is what makes an object iterable. Behind the scenes, the iter function calls __iter__ method on the given object.

The return value of __iter__ is an iterator. It should have a next method and raise StopIteration when there are no more elements.

Lets try it out:'''


class yrange:
    def __init__(self, n):
        self.i = 0
        self.n = n

    def __iter__(self):
        return self

    def next(self):
        if self.i < self.n:
            i = self.i
            self.i += 1
            return i
        else:
            raise StopIteration()
            
x = yrange(3)
print(x.next())
print(x.next())
print(x.next())

####Many built-in functions accept iterators as arguments.

'''In the above case, both the iterable and iterator are the same object. Notice that the __iter__ method returned self. It need not be the case always.'''

class zrange:
    def __init__(self, n):
        self.n = n

    def __iter__(self):
        return zrange_iter(self.n)

class zrange_iter:
    def __init__(self, n):
        self.i = 0
        self.n = n

    def __iter__(self):
        # Iterators are iterables too.
        # Adding this functions to make them so.
        return self

    def next(self):
        if self.i < self.n:
            i = self.i
            self.i += 1
            return i
        else:
            raise StopIteration()
            
z = zrange(3)
zz=zrange_iter(3)
print(z)
print(zz)
print(zz.next())
list(z) ##################### Run on Python 2.5.1 
####################################################################################### List Comprehension

sentence = "the quick brown fox jumps over the lazy dog"
words = sentence.split()
word_lengths = []
for word in words:
    if word != "the":
        word_lengths.append(len(word))
        
print(word_lengths)        
############# List Comprehension (List understood without code for loop)
word_lengths = [len(word) for word in words if word != "the"]
print(word_lengths)  

###### List to create positive number from another list 
numbers = [34.6, -203.4, 44.9, 68.3, -12.2, 44.6, 12.7]
newlist = [int(x) for x in numbers if x > 0]
print(newlist) 


############################################################### Multiple function arg by Tuple

def foo(first, second, third, *therest):
    print ("First: %s" % first)
    print ("Second: %s" % second)
    print ("Third: %s" % third)
    print ("And all the rest... %s" % list(therest))
    print(therest)  ########## to show that it's a tuple

foo (1,2,3,4,5,6,7)    
tup = (1,2,3,4,5)
print(tup)
print(list(tup))

######################### Arguments by Keyword using dictionary
def bar(first, second, third, **options): ##### See the double *
    print(options) ###### to show that it's a dict
    if options.get("action") == "sum":
        print ("The sum is: %d" % (first + second + third))
    if options.get("number") == "first":
        return first

result = bar(1, 2, 3, action = "sum", number = "first")

print ("Result: %d " %result)

arg = {"a":1,"b":2,"c":3}
print(arg["a"])
'''
 the foo and bar functions so they can receive a variable amount of arguments (3 or more) The foo function must return the amount of extra arguments received. The bar must return True if the argument with the keyword magicnumber is worth 7, and False otherwise.'''
 
# edit the functions prototype and implementation
def foo(a, b, c, *args):
    return len(args)

def bar(a, b, c, **kwargs):
    return kwargs["magicnumber"] == 7


# test code
tup = {1,2,3,4}
print(len(tup))

if foo(1,2,3,4) == 1:
    print ("Good.")
if foo(1,2,3,4,5) == 2:
    print ("Better.")
if bar(1,2,3,magicnumber = 6) == False:
    print ("Great.")
if bar(1,2,3,magicnumber = 7) == True:
    print ("Awesome!")



################################## Regular Expression
# Exercise: make a regular expression that will match an email
import re
def test_email(your_pattern):
    pattern = re.compile(your_pattern)
    emails = ["john@example.com", "python-list@python.org", "wha.t.`1an?ug{}ly@email.com"]
    for email in emails:
        if not re.match(pattern, email):
            print ("You failed to match %s" % (email))
        elif not your_pattern:
            print ("Forgot to enter a pattern!")
        else:
            print ("Pass")
# Your pattern here!
pattern = r"\"?([-a-zA-Z0-9.`?{}]+@\w+\.\w+)\"?"
test_email(pattern)

#################################################################### Exception Handling
def do_stuff_with_number(n):
    print (n)

the_list = (1, 2, 3, 4, 5)

for i in range(20):
    try:
        do_stuff_with_number(the_list[i])
    except IndexError: # Raised when accessing a non-existing index of a list
        do_stuff_with_number(0)
        

def get_last_name(x):
    return actor["name"].split()[x]
try:
    for i in range(0,len(actor)+2): ## +2 to raise exception
        get_last_name(i)
        print ("The actor's last name is %s" % get_last_name(i)) 
except IndexError:
        print ("All exceptions caught! Good job!")

print("Sample String".split()[1])
splitt= "Sample String".split()
print(splitt)
    
    
#################################################################################### Sets
##Sets are lists with no duplicate entries. Let's say you want to collect a list of words used in a paragraph:

print (set("my name is Eric and Eric is my name".split()))


a = set(["Jake", "John", "Eric"])
b = set(["John", "Jill"])

#To find out which members attended both events, you may use the "intersection" method:
print("Intersection ",a.intersection(b))
print("Intersection ",b.intersection(a))

#To find out which members attended only one of the events, use the "symmetric_difference" method:
print("symmetric difference ",a.symmetric_difference(b))
print("symmetric difference ",b.symmetric_difference(a))

#To find out which members attended only one event and not the other, use the "difference" method:
print("Difference",a.difference(b))
print("Difference",b.difference(a))
#To receive a list of all participants, use the "union" method:
print( "Union ",a.union(b))

listt =[1,2,3,4,4]
tuplee =(1,2,3,4,4)
dictt={"A":1,"B":2,"C":3,"D":4,"E":4,"E":5}
print("list to set" , set(listt))
print("tuple to set ", set(tuplee))
print("dictionary to set ",set(dictt))
print(dictt["A"])

########################################################################json

###To encode a data structure to JSON, use the "dumps" method. This method takes an object and returns a String:
json_string = json.dumps([1, 2, 3, "a", "b", "c"])
###To load JSON back to a data structure, use the "loads" method. This method takes a string and turns it back into the json object datastructure:
print (json.loads(json_string))

import json

# fix this function, so it adds the given name
# and salary pair to salaries_json, and return it
def add_employee(salaries_json, name, salary):
    salaries = json.loads(salaries_json)
    salaries[name] = salary

    return json.dumps(salaries)

# test code
salaries = '{"Alfred" : 300, "Jane" : 400 }'
new_salaries = add_employee(salaries, "Me", 800)
decoded_salaries = json.loads(new_salaries)
print decoded_salaries["Alfred"]
print decoded_salaries["Jane"]
print decoded_salaries["Me"]


import json
##### Json object here is array of json objects
json_string = json.dumps({"employees":[
    {"firstName":"John", "lastName":"Doe"},
    {"firstName":"Anna", "lastName":"Smith"},
    {"firstName":"Peter", "lastName":"Jones"}
]})
#print(json_string)
jsonn=json.loads(json_string)
#print(jsonn)
print(jsonn["employees"][0])
print(jsonn["employees"][0]["firstName"])

##########################################
######Slicing########
str ='123456789' ;
print(str[:5]) ;
print(str[5:]);
