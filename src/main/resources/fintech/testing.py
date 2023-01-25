from skforecast.utils import load_forecaster
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import requests
import json
from xgboost import XGBRegressor


def testing(df: pd.DataFrame):
    dict = []
    for provincia in df['ITTER107'].unique():

        for tipo in df['TIPO_ALLOGGIO2'].unique():

            for paesi in df['ISO'].unique():
                df_train = df[df['ITTER107'] == provincia][df['TIPO_ALLOGGIO2'] == tipo][
                    df['ISO'] == paesi][df['Indicatori'] == 'presenze'].fillna(0)

                df_train = df_train.set_index(pd.DatetimeIndex(df_train['TIME'], freq='MS'))

                modello = load_forecaster(f'./prediction-models/{provincia}/{tipo}/{paesi}.py')
                pred = modello.predict(steps=24)

                # plt.figure(figsize=(12, 8))
                # plt.plot(df_train.index, df_train['Value'])
                # plt.plot(pred.index, pred)
                # plt.title(f'{provincia},{tipo},{paesi}')
                # plt.show()
                print(round(pred, 3))

                for x in pred.index :
                    dict.append({
                        "provincia" : provincia,
                        "tipoAlloggio" : tipo,
                        "residenzaClienti" : paesi,
                        "time" : str(x.year) + "-" + str(x.month),
                        "value" : pred[x]
                    })
    return dict


if __name__ == '__main__':
    df = pd.read_json('./src/main/resources/fintech/dataset/dataset_con_sostituzione.json', orient='record')

    df = df.drop(['Flag Codes', 'Flags'], axis=1)

    dict = testing(df)

    # str = json.dump(dict)
    # file = open("./prova.json", "w")
    # file.write(str)
    # file.close()


    url = 'http://localhost:8085/gruppo8/fintech/upload'

    x = requests.post(url, json = dict)

    print(x.text)