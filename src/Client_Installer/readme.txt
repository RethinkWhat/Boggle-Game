# FINAL TEAM 4
1. Download Python 3.8.

2. Download Python 3.10.
    2.1. Add the path to the scripts folder under this Python version to the environment variables
    2.2. Add the path to the bin folder under this Python version to the environment variables

2. Download omniORB from https://omniorb.sourceforge.io/ and add to environment variables. When adding
    to the environment variables include the bin folder and the architecture to path. Should look like this:
    C:\Users\user\Desktop\omniORBpy-4.3.0\bin\x86_win32

3. Verify the python version you are running (should be 3.8 or 3.10). The two python versions will be used
    for separate purposes. Python 3.10 will be used for generating the stubs by issuing idl commands, while Python
    3.8 will be used for client connections

4. Verify the omniORB installation byy typing the command omniidl -u, which should display a list of commands.

5. Open Pycharm and set up interpreter to use python 3.10.

6. Under the interpreter install the package omniorb-py.

7. To generate stubs (use Python 3.10) issue the command omniidl -bpython boggleClient.idl