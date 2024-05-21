# Python stubs generated by omniidl from BoggleClient.idl
# DO NOT EDIT THIS FILE!

import omniORB, _omnipy
from omniORB import CORBA, PortableServer
_0_CORBA = CORBA


_omnipy.checkVersion(4,2, __file__, 1)

try:
    property
except NameError:
    def property(*args):
        return None


#
# Start of module "BoggleApp"
#
__name__ = "BoggleApp"
_0_BoggleApp = omniORB.openModule("BoggleApp", r"BoggleClient.idl")
_0_BoggleApp__POA = omniORB.openModule("BoggleApp__POA", r"BoggleClient.idl")


# struct userInfo
_0_BoggleApp.userInfo = omniORB.newEmptyClass()
class userInfo (omniORB.StructBase):
    _NP_RepositoryId = "IDL:BoggleApp/userInfo:1.0"

    def __init__(self, username, pfpAddress, points):
        self.username = username
        self.pfpAddress = pfpAddress
        self.points = points

_0_BoggleApp.userInfo = userInfo
_0_BoggleApp._d_userInfo  = (omniORB.tcInternal.tv_struct, userInfo, userInfo._NP_RepositoryId, "userInfo", "username", (omniORB.tcInternal.tv_string,0), "pfpAddress", (omniORB.tcInternal.tv_string,0), "points", omniORB.tcInternal.tv_long)
_0_BoggleApp._tc_userInfo = omniORB.tcInternal.createTypeCode(_0_BoggleApp._d_userInfo)
omniORB.registerType(userInfo._NP_RepositoryId, _0_BoggleApp._d_userInfo, _0_BoggleApp._tc_userInfo)
del userInfo

# struct LobbyUser
_0_BoggleApp.LobbyUser = omniORB.newEmptyClass()
class LobbyUser (omniORB.StructBase):
    _NP_RepositoryId = "IDL:BoggleApp/LobbyUser:1.0"

    def __init__(self, username, pfpAddress):
        self.username = username
        self.pfpAddress = pfpAddress

_0_BoggleApp.LobbyUser = LobbyUser
_0_BoggleApp._d_LobbyUser  = (omniORB.tcInternal.tv_struct, LobbyUser, LobbyUser._NP_RepositoryId, "LobbyUser", "username", (omniORB.tcInternal.tv_string,0), "pfpAddress", (omniORB.tcInternal.tv_string,0))
_0_BoggleApp._tc_LobbyUser = omniORB.tcInternal.createTypeCode(_0_BoggleApp._d_LobbyUser)
omniORB.registerType(LobbyUser._NP_RepositoryId, _0_BoggleApp._d_LobbyUser, _0_BoggleApp._tc_LobbyUser)
del LobbyUser

# typedef ... leaderboards
class leaderboards:
    _NP_RepositoryId = "IDL:BoggleApp/leaderboards:1.0"
    def __init__(self, *args, **kw):
        raise RuntimeError("Cannot construct objects of this type.")
_0_BoggleApp.leaderboards = leaderboards
_0_BoggleApp._d_leaderboards  = (omniORB.tcInternal.tv_sequence, omniORB.typeMapping["IDL:BoggleApp/userInfo:1.0"], 0)
_0_BoggleApp._ad_leaderboards = (omniORB.tcInternal.tv_alias, leaderboards._NP_RepositoryId, "leaderboards", (omniORB.tcInternal.tv_sequence, omniORB.typeMapping["IDL:BoggleApp/userInfo:1.0"], 0))
_0_BoggleApp._tc_leaderboards = omniORB.tcInternal.createTypeCode(_0_BoggleApp._ad_leaderboards)
omniORB.registerType(leaderboards._NP_RepositoryId, _0_BoggleApp._ad_leaderboards, _0_BoggleApp._tc_leaderboards)
del leaderboards

# typedef ... lobbyUserList
class lobbyUserList:
    _NP_RepositoryId = "IDL:BoggleApp/lobbyUserList:1.0"
    def __init__(self, *args, **kw):
        raise RuntimeError("Cannot construct objects of this type.")
_0_BoggleApp.lobbyUserList = lobbyUserList
_0_BoggleApp._d_lobbyUserList  = (omniORB.tcInternal.tv_sequence, omniORB.typeMapping["IDL:BoggleApp/LobbyUser:1.0"], 0)
_0_BoggleApp._ad_lobbyUserList = (omniORB.tcInternal.tv_alias, lobbyUserList._NP_RepositoryId, "lobbyUserList", (omniORB.tcInternal.tv_sequence, omniORB.typeMapping["IDL:BoggleApp/LobbyUser:1.0"], 0))
_0_BoggleApp._tc_lobbyUserList = omniORB.tcInternal.createTypeCode(_0_BoggleApp._ad_lobbyUserList)
omniORB.registerType(lobbyUserList._NP_RepositoryId, _0_BoggleApp._ad_lobbyUserList, _0_BoggleApp._tc_lobbyUserList)
del lobbyUserList

# typedef ... wordList
class wordList:
    _NP_RepositoryId = "IDL:BoggleApp/wordList:1.0"
    def __init__(self, *args, **kw):
        raise RuntimeError("Cannot construct objects of this type.")
_0_BoggleApp.wordList = wordList
_0_BoggleApp._d_wordList  = (omniORB.tcInternal.tv_sequence, (omniORB.tcInternal.tv_string,0), 0)
_0_BoggleApp._ad_wordList = (omniORB.tcInternal.tv_alias, wordList._NP_RepositoryId, "wordList", (omniORB.tcInternal.tv_sequence, (omniORB.tcInternal.tv_string,0), 0))
_0_BoggleApp._tc_wordList = omniORB.tcInternal.createTypeCode(_0_BoggleApp._ad_wordList)
omniORB.registerType(wordList._NP_RepositoryId, _0_BoggleApp._ad_wordList, _0_BoggleApp._tc_wordList)
del wordList

# exception accountLoggedIn
_0_BoggleApp.accountLoggedIn = omniORB.newEmptyClass()
class accountLoggedIn (CORBA.UserException):
    _NP_RepositoryId = "IDL:BoggleApp/accountLoggedIn:1.0"

    def __init__(self, reason):
        CORBA.UserException.__init__(self, reason)
        self.reason = reason

_0_BoggleApp.accountLoggedIn = accountLoggedIn
_0_BoggleApp._d_accountLoggedIn  = (omniORB.tcInternal.tv_except, accountLoggedIn, accountLoggedIn._NP_RepositoryId, "accountLoggedIn", "reason", (omniORB.tcInternal.tv_string,0))
_0_BoggleApp._tc_accountLoggedIn = omniORB.tcInternal.createTypeCode(_0_BoggleApp._d_accountLoggedIn)
omniORB.registerType(accountLoggedIn._NP_RepositoryId, _0_BoggleApp._d_accountLoggedIn, _0_BoggleApp._tc_accountLoggedIn)
del accountLoggedIn

# exception accountDoesNotExist
_0_BoggleApp.accountDoesNotExist = omniORB.newEmptyClass()
class accountDoesNotExist (CORBA.UserException):
    _NP_RepositoryId = "IDL:BoggleApp/accountDoesNotExist:1.0"

    def __init__(self, reason):
        CORBA.UserException.__init__(self, reason)
        self.reason = reason

_0_BoggleApp.accountDoesNotExist = accountDoesNotExist
_0_BoggleApp._d_accountDoesNotExist  = (omniORB.tcInternal.tv_except, accountDoesNotExist, accountDoesNotExist._NP_RepositoryId, "accountDoesNotExist", "reason", (omniORB.tcInternal.tv_string,0))
_0_BoggleApp._tc_accountDoesNotExist = omniORB.tcInternal.createTypeCode(_0_BoggleApp._d_accountDoesNotExist)
omniORB.registerType(accountDoesNotExist._NP_RepositoryId, _0_BoggleApp._d_accountDoesNotExist, _0_BoggleApp._tc_accountDoesNotExist)
del accountDoesNotExist

# exception updateFailed
_0_BoggleApp.updateFailed = omniORB.newEmptyClass()
class updateFailed (CORBA.UserException):
    _NP_RepositoryId = "IDL:BoggleApp/updateFailed:1.0"

    def __init__(self, reason):
        CORBA.UserException.__init__(self, reason)
        self.reason = reason

_0_BoggleApp.updateFailed = updateFailed
_0_BoggleApp._d_updateFailed  = (omniORB.tcInternal.tv_except, updateFailed, updateFailed._NP_RepositoryId, "updateFailed", "reason", (omniORB.tcInternal.tv_string,0))
_0_BoggleApp._tc_updateFailed = omniORB.tcInternal.createTypeCode(_0_BoggleApp._d_updateFailed)
omniORB.registerType(updateFailed._NP_RepositoryId, _0_BoggleApp._d_updateFailed, _0_BoggleApp._tc_updateFailed)
del updateFailed

# interface BoggleClient
_0_BoggleApp._d_BoggleClient = (omniORB.tcInternal.tv_objref, "IDL:BoggleApp/BoggleClient:1.0", "BoggleClient")
omniORB.typeMapping["IDL:BoggleApp/BoggleClient:1.0"] = _0_BoggleApp._d_BoggleClient
_0_BoggleApp.BoggleClient = omniORB.newEmptyClass()
class BoggleClient :
    _NP_RepositoryId = _0_BoggleApp._d_BoggleClient[1]

    def __init__(self, *args, **kw):
        raise RuntimeError("Cannot construct objects of this type.")

    _nil = CORBA.Object._nil


_0_BoggleApp.BoggleClient = BoggleClient
_0_BoggleApp._tc_BoggleClient = omniORB.tcInternal.createTypeCode(_0_BoggleApp._d_BoggleClient)
omniORB.registerType(BoggleClient._NP_RepositoryId, _0_BoggleApp._d_BoggleClient, _0_BoggleApp._tc_BoggleClient)

# BoggleClient operations and attributes
BoggleClient._d_validateAccount = (((omniORB.tcInternal.tv_string,0), (omniORB.tcInternal.tv_string,0)), (), {_0_BoggleApp.accountLoggedIn._NP_RepositoryId: _0_BoggleApp._d_accountLoggedIn, _0_BoggleApp.accountDoesNotExist._NP_RepositoryId: _0_BoggleApp._d_accountDoesNotExist})
BoggleClient._d_attemptJoin = (((omniORB.tcInternal.tv_string,0), ), (), None)
BoggleClient._d_getLobbyMembers = ((), (omniORB.typeMapping["IDL:BoggleApp/lobbyUserList:1.0"], ), None)
BoggleClient._d_getCurrLobbyTimerValue = ((), (omniORB.tcInternal.tv_longlong, omniORB.tcInternal.tv_boolean), None)
BoggleClient._d_getGameID = (((omniORB.tcInternal.tv_string,0), ), (omniORB.tcInternal.tv_long, ), None)
BoggleClient._d_getLetters = ((omniORB.tcInternal.tv_long, ), ((omniORB.tcInternal.tv_string,0), ), None)
BoggleClient._d_getGameDurationVal = ((omniORB.tcInternal.tv_long, ), (omniORB.tcInternal.tv_longlong, ), None)
BoggleClient._d_sendUserWordList = ((omniORB.tcInternal.tv_long, (omniORB.tcInternal.tv_string,0), omniORB.typeMapping["IDL:BoggleApp/wordList:1.0"]), (), None)
BoggleClient._d_getRoundWinner = ((omniORB.tcInternal.tv_long, ), ((omniORB.tcInternal.tv_string,0), ), None)
BoggleClient._d_getOverallWinner = ((omniORB.tcInternal.tv_long, ), ((omniORB.tcInternal.tv_string,0), ), None)
BoggleClient._d_getLeaderboard = ((), (omniORB.typeMapping["IDL:BoggleApp/leaderboards:1.0"], ), None)
BoggleClient._d_editInfo = (((omniORB.tcInternal.tv_string,0), (omniORB.tcInternal.tv_string,0), (omniORB.tcInternal.tv_string,0)), (), {_0_BoggleApp.updateFailed._NP_RepositoryId: _0_BoggleApp._d_updateFailed})
BoggleClient._d_editPassword = (((omniORB.tcInternal.tv_string,0), (omniORB.tcInternal.tv_string,0), (omniORB.tcInternal.tv_string,0)), (), {_0_BoggleApp.updateFailed._NP_RepositoryId: _0_BoggleApp._d_updateFailed})
BoggleClient._d_getUserTotalPoints = (((omniORB.tcInternal.tv_string,0), ), (omniORB.tcInternal.tv_long, ), None)
BoggleClient._d_getUserPointsOngoingGame = ((omniORB.tcInternal.tv_long, (omniORB.tcInternal.tv_string,0)), (omniORB.tcInternal.tv_long, ), None)
BoggleClient._d_getNumberOfMatches = (((omniORB.tcInternal.tv_string,0), ), (omniORB.tcInternal.tv_long, ), None)
BoggleClient._d_getNumberOfWins = (((omniORB.tcInternal.tv_string,0), ), (omniORB.tcInternal.tv_long, ), None)
BoggleClient._d_getCurrGameLeaderboard = ((omniORB.tcInternal.tv_long, ), (omniORB.typeMapping["IDL:BoggleApp/leaderboards:1.0"], ), None)
BoggleClient._d_getPFPOFUser = (((omniORB.tcInternal.tv_string,0), ), ((omniORB.tcInternal.tv_string,0), ), None)
BoggleClient._d_getFullName = (((omniORB.tcInternal.tv_string,0), ), ((omniORB.tcInternal.tv_string,0), ), None)
BoggleClient._d_isValidWord = (((omniORB.tcInternal.tv_string,0), ), (omniORB.tcInternal.tv_boolean, ), None)
BoggleClient._d_exitLobby = (((omniORB.tcInternal.tv_string,0), ), (), None)
BoggleClient._d_logout = (((omniORB.tcInternal.tv_string,0), ), (), None)

# BoggleClient object reference
class _objref_BoggleClient (CORBA.Object):
    _NP_RepositoryId = BoggleClient._NP_RepositoryId

    def __init__(self, obj):
        CORBA.Object.__init__(self, obj)

    def validateAccount(self, *args):
        return self._obj.invoke("validateAccount", _0_BoggleApp.BoggleClient._d_validateAccount, args)

    def attemptJoin(self, *args):
        return self._obj.invoke("attemptJoin", _0_BoggleApp.BoggleClient._d_attemptJoin, args)

    def getLobbyMembers(self, *args):
        return self._obj.invoke("getLobbyMembers", _0_BoggleApp.BoggleClient._d_getLobbyMembers, args)

    def getCurrLobbyTimerValue(self, *args):
        return self._obj.invoke("getCurrLobbyTimerValue", _0_BoggleApp.BoggleClient._d_getCurrLobbyTimerValue, args)

    def getGameID(self, *args):
        return self._obj.invoke("getGameID", _0_BoggleApp.BoggleClient._d_getGameID, args)

    def getLetters(self, *args):
        return self._obj.invoke("getLetters", _0_BoggleApp.BoggleClient._d_getLetters, args)

    def getGameDurationVal(self, *args):
        return self._obj.invoke("getGameDurationVal", _0_BoggleApp.BoggleClient._d_getGameDurationVal, args)

    def sendUserWordList(self, *args):
        return self._obj.invoke("sendUserWordList", _0_BoggleApp.BoggleClient._d_sendUserWordList, args)

    def getRoundWinner(self, *args):
        return self._obj.invoke("getRoundWinner", _0_BoggleApp.BoggleClient._d_getRoundWinner, args)

    def getOverallWinner(self, *args):
        return self._obj.invoke("getOverallWinner", _0_BoggleApp.BoggleClient._d_getOverallWinner, args)

    def getLeaderboard(self, *args):
        return self._obj.invoke("getLeaderboard", _0_BoggleApp.BoggleClient._d_getLeaderboard, args)

    def editInfo(self, *args):
        return self._obj.invoke("editInfo", _0_BoggleApp.BoggleClient._d_editInfo, args)

    def editPassword(self, *args):
        return self._obj.invoke("editPassword", _0_BoggleApp.BoggleClient._d_editPassword, args)

    def getUserTotalPoints(self, *args):
        return self._obj.invoke("getUserTotalPoints", _0_BoggleApp.BoggleClient._d_getUserTotalPoints, args)

    def getUserPointsOngoingGame(self, *args):
        return self._obj.invoke("getUserPointsOngoingGame", _0_BoggleApp.BoggleClient._d_getUserPointsOngoingGame, args)

    def getNumberOfMatches(self, *args):
        return self._obj.invoke("getNumberOfMatches", _0_BoggleApp.BoggleClient._d_getNumberOfMatches, args)

    def getNumberOfWins(self, *args):
        return self._obj.invoke("getNumberOfWins", _0_BoggleApp.BoggleClient._d_getNumberOfWins, args)

    def getCurrGameLeaderboard(self, *args):
        return self._obj.invoke("getCurrGameLeaderboard", _0_BoggleApp.BoggleClient._d_getCurrGameLeaderboard, args)

    def getPFPOFUser(self, *args):
        return self._obj.invoke("getPFPOFUser", _0_BoggleApp.BoggleClient._d_getPFPOFUser, args)

    def getFullName(self, *args):
        return self._obj.invoke("getFullName", _0_BoggleApp.BoggleClient._d_getFullName, args)

    def isValidWord(self, *args):
        return self._obj.invoke("isValidWord", _0_BoggleApp.BoggleClient._d_isValidWord, args)

    def exitLobby(self, *args):
        return self._obj.invoke("exitLobby", _0_BoggleApp.BoggleClient._d_exitLobby, args)

    def logout(self, *args):
        return self._obj.invoke("logout", _0_BoggleApp.BoggleClient._d_logout, args)

omniORB.registerObjref(BoggleClient._NP_RepositoryId, _objref_BoggleClient)
_0_BoggleApp._objref_BoggleClient = _objref_BoggleClient
del BoggleClient, _objref_BoggleClient

# BoggleClient skeleton
__name__ = "BoggleApp__POA"
class BoggleClient (PortableServer.Servant):
    _NP_RepositoryId = _0_BoggleApp.BoggleClient._NP_RepositoryId


    _omni_op_d = {"validateAccount": _0_BoggleApp.BoggleClient._d_validateAccount, "attemptJoin": _0_BoggleApp.BoggleClient._d_attemptJoin, "getLobbyMembers": _0_BoggleApp.BoggleClient._d_getLobbyMembers, "getCurrLobbyTimerValue": _0_BoggleApp.BoggleClient._d_getCurrLobbyTimerValue, "getGameID": _0_BoggleApp.BoggleClient._d_getGameID, "getLetters": _0_BoggleApp.BoggleClient._d_getLetters, "getGameDurationVal": _0_BoggleApp.BoggleClient._d_getGameDurationVal, "sendUserWordList": _0_BoggleApp.BoggleClient._d_sendUserWordList, "getRoundWinner": _0_BoggleApp.BoggleClient._d_getRoundWinner, "getOverallWinner": _0_BoggleApp.BoggleClient._d_getOverallWinner, "getLeaderboard": _0_BoggleApp.BoggleClient._d_getLeaderboard, "editInfo": _0_BoggleApp.BoggleClient._d_editInfo, "editPassword": _0_BoggleApp.BoggleClient._d_editPassword, "getUserTotalPoints": _0_BoggleApp.BoggleClient._d_getUserTotalPoints, "getUserPointsOngoingGame": _0_BoggleApp.BoggleClient._d_getUserPointsOngoingGame, "getNumberOfMatches": _0_BoggleApp.BoggleClient._d_getNumberOfMatches, "getNumberOfWins": _0_BoggleApp.BoggleClient._d_getNumberOfWins, "getCurrGameLeaderboard": _0_BoggleApp.BoggleClient._d_getCurrGameLeaderboard, "getPFPOFUser": _0_BoggleApp.BoggleClient._d_getPFPOFUser, "getFullName": _0_BoggleApp.BoggleClient._d_getFullName, "isValidWord": _0_BoggleApp.BoggleClient._d_isValidWord, "exitLobby": _0_BoggleApp.BoggleClient._d_exitLobby, "logout": _0_BoggleApp.BoggleClient._d_logout}

BoggleClient._omni_skeleton = BoggleClient
_0_BoggleApp__POA.BoggleClient = BoggleClient
omniORB.registerSkeleton(BoggleClient._NP_RepositoryId, BoggleClient)
del BoggleClient
__name__ = "BoggleApp"

#
# End of module "BoggleApp"
#
__name__ = "BoggleClient_idl"

_exported_modules = ( "BoggleApp", )

# The end.