import time
from datetime import datetime, timedelta


def main():
    """
    ○○秒ちょうどに何か実行する

    :return:
    """
    delta = timedelta(seconds=1)

    while True:
        start = datetime.now()
        start += delta
        start = start.replace(microsecond=0)
        diff = start - datetime.now()
        diff_sec = diff.seconds + (diff.microseconds / 1000000)
        time.sleep(diff_sec)
        now = datetime.now()
        print("start:{}\tnow:{}\tdiff:{}".format(start, now, now - start))


if __name__ == '__main__':
    main()
