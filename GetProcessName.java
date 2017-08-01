@Override
public void onCreate() {
    super.onCreate();

    String processName = AppUtility.getProcessName(Process.myPid());
    if (getPackageName().equals(processName)) {
        // init
    }
  
}


/**
* 根据进程 ID 获取进程名
* @param pid
* @return
*/
public static String getProcessName(int pid){
  ActivityManager am = (ActivityManager) MainApplication.getContext().getSystemService(Context.ACTIVITY_SERVICE);
  List<RunningAppProcessInfo> processInfoList = am.getRunningAppProcesses();
  if (processInfoList == null) {
    return null;
  }
  for (RunningAppProcessInfo processInfo : processInfoList) {
    if (processInfo.pid == pid) {
      return processInfo.processName;
    }
  }
  return null;
}



//除了利用 ActivityManager 服务获取当前进程名，还有一种黑科技
public static String getProcessName() {
  try {
    File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
    BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
    String processName = mBufferedReader.readLine().trim();
    mBufferedReader.close();
    return processName;
  } catch (Exception e) {
    e.printStackTrace();
    return null;
  }
}