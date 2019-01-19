import time
import cv2
from os import path

color = (255, 255, 255)
BASE_DIR = path.dirname(__file__)
CASCADE_PATH = path.join(BASE_DIR, 'lbpcascade_animeface.xml')
if not path.isfile(CASCADE_PATH):
    raise RuntimeError('%s: not found.'.format(CASCADE_PATH))

VIDEO_PATH = path.join(BASE_DIR, '1.mov')

cascade = cv2.CascadeClassifier(CASCADE_PATH)

capture = cv2.VideoCapture(VIDEO_PATH)

while True:
    ret, frame = capture.read()
    if not ret:
        break

    frame = cv2.cvtColor(frame, cv2.COLOR_RGB2GRAY)
    facerect = cascade.detectMultiScale(frame, scaleFactor=1.1, minNeighbors=5, minSize=(24, 24))
    print(facerect)
    for rect in facerect:
        cv2.rectangle(frame, tuple(rect[0:2]), tuple(rect[0:2] + rect[2:4]), color, thickness=2)

    cv2.imshow("Capture", frame)
    cv2.waitKey(5)

cv2.destroyAllWindows()
