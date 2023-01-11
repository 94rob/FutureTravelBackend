import json

# some JSON:
x =  '[{"Territorio" : "Caserta","Tipo_alloggio" : "HOTELLIKE","Residenza_clienti" : "IT", "Time" : "2008-01", "Presenze" : "12874"}, {"Territorio" : "Caserta", "Tipo_alloggio" : "HOTELLIKE", "Residenza_clienti" : "IT", "Time" : "2008-01", "Presenze" : "12874"}]'

# parse x:
y = json.loads(x)

# the result is a Python dictionary:
print(y)
