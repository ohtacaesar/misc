import time
import cv2
from PyQt4 import QtCore, QtGui

color = (255, 255, 255)
CASCADE_PATH = '/usr/local/opt/opencv3/share/OpenCV/haarcascades/haarcascade_frontalface_alt.xml'
cascade = cv2.CascadeClassifier(CASCADE_PATH)


class Capture:
    def __init__(self):
        self.capturing = False
        self.c = cv2.VideoCapture(0)

    def start(self):
        self.capturing = True
        cap = self.c
        while self.capturing:
            ret, frame = cap.read()
            frame = cv2.cvtColor(frame, cv2.COLOR_RGB2GRAY)
            facerect = cascade.detectMultiScale(frame, scaleFactor=1.2, minNeighbors=2, minSize=(10, 10))
            print(facerect)
            for rect in facerect:
                cv2.rectangle(frame, tuple(rect[0:2]), tuple(rect[0:2] + rect[2:4]), color, thickness=2)

            cv2.imshow("Capture", frame)
            cv2.waitKey(5)
            time.sleep(0.1)
        cv2.destroyAllWindows()

    def stop(self):
        self.capturing = False

    def quit(self):
        cap = self.c
        cv2.destroyAllWindows()
        cap.release()
        QtCore.QCoreApplication.quit()


class Window(QtGui.QWidget):
    def __init__(self):
        super(Window, self).__init__()

        self.capture = Capture()
        self.start_button = QtGui.QPushButton('Start', self)
        self.start_button.clicked.connect(self.capture.start)

        self.stop_button = QtGui.QPushButton('Stop', self)
        self.stop_button.clicked.connect(self.capture.stop)

        self.quit_button = QtGui.QPushButton('Quit', self)
        self.quit_button.clicked.connect(self.capture.quit)

        vbox = QtGui.QVBoxLayout(self)
        vbox.addWidget(self.start_button)
        vbox.addWidget(self.stop_button)
        vbox.addWidget(self.quit_button)

        self.setLayout(vbox)
        self.setGeometry(100, 100, 200, 200)
        self.show()


if __name__ == '__main__':
    import sys

    app = QtGui.QApplication(sys.argv)
    window = Window()
    sys.exit(app.exec_())
