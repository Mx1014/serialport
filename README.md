# serialport" 
java 串口通讯程序 基于RXTX进行串口通讯 与CH9329串口芯片模拟外设键盘输入
通过mqtt进行数据传输


树莓派 配置串口 与rxtx
1.树莓派开启串口 
树莓派包含两个串口，一个称之为硬件串口（/dev/ttyAMA0），一个称之为mini串口（/dev/ttyS0）。硬件串口由硬件实现，有单独的波特率时钟源，性能高、可靠。mini串口时钟源是由CPU内核时钟提供，波特率受到内核时钟的影响，不稳定。想要通过树莓派的GPIO引脚进行稳定的串口通信，需要修改串口的映射关系。
serial0是GPIO引脚对应的串口，serial1是蓝牙对应的串口。
操作前先看下默认的映射关系，使用指令：
ls -l /dev
这是没打开GPIO串口的情况，只有serial1（蓝牙）使用的是ttyAMA0(硬件串口)：

接下来打开GPIO串口，这个就不贴图了，比较简单。执行
sudo raspi-config
找到Interfacing选项，找到serial。
第一个问题是：would you like a login shell to be accessible  over serial? 选否。
第二个问题是：would you like the serial port hardware to be enabled? 选是。

serial0（GPIO串口）默认使用的是ttyS0(mini串口)，serial1（蓝牙）使用的是ttyAMA0（硬件串口）。
如果想使用稳定可靠的硬件串口，就要将树莓派3的硬件串口与mini串口默认映射对换。
而这个需求官方也考虑到了，在系统中放了一个实现这个功能的文件。
Jessie版本系统中的文件为/boot/overlays/pi3-miniuart-bt-overlay.dtb，stretch版本系统中的文件为/boot/overlays/pi3-miniuart-bt.dtbo。
使用该文件发挥功能只需在/boot/config.txt文件末尾添加一行代码。
打开文件，
sudo  vim  /boot/config.txt
在末尾添加这行代码：  
dtoverlay=pi3-miniuart-bt
修改完以后需要重启，操作完成后再来看下映射关系：

如图所示，serial0（GPIO串口）使用的是ttyAMA0(硬件串口)，而serial1（蓝牙）使用的是ttys0(mini串口)。大功告成！

2.树莓派安装openjdk 
先更新源，然后安装openjdk，安装完成后，配置Java环境变量
export JAVA_HOME= （安装位置 默认为 /usr/lib/jvm/java-8-xxx）
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
更新完成后，下载RXTX源码包
wget http://rxtx.qbang.org/pub/rxtx/rxtx-2.1-7r2.zip
unzip rxtx-2.1-7r2.zip
cd rxtx-2.1-7r2
解压完成后，需要修改下面列了个可能报错的信息
编译过程中可能遇到的问题
错误一:
rxtx-2.1-7r2/./src/RS485Imp.c:96:25: error: 'UTS_RELEASE' undeclared (first use in this function)
  if(strcmp(name.release,UTS_RELEASE)!=0)
这是由于 version.h 中缺少 UTS_RELEASE 信息，需要手工添加。先获取当前系统的版本信息：
 uname -r 
然后在 /usr/include/linux/version.h 中添加
#define UTS_RELEASE "4.14.34-v7+" 
 4.14.34-v7+ 为上一步中获得的版本号.
--------------------------------------------------------------------------------
错误二:
libtool: install: armv6l-unknown-linux-gnu/librxtxRS485.la’ is not a directory 
这个错误会出现在JDK1.6及以上的运行环境下，需要对configure文件进行修改。在configure文件中找到所有的 1.2*|1.3*|1.4*|1.5* ,将现有的JDK版本加入进去即可.如:改成 1.2*|1.3*|1.4*|1.5*|1.6*|1.7*|1.8* .

--------------------------------------------------------------------------------
错误三:
在后期rxtx使用过程中，可能遇到无法找到串口的问题，这是因为rxtx中在Linux环境下未添加/dev/ttyACMx类型的串口查询，建议根据实际串口名称在源码中进行修改：

1.修改源码：参考官网故障排除http://rxtx.qbang.org/wiki/index.php/Trouble_shooting（中间部分）
源码RXTXCommDriver.java文件中518行开始，为Linux系统环境下的串口名称查找，添加所需串口名即可（如ttyAMA）（此种方法需要重新编译安装）
2.将串口文件进行映射，即使用命令 ln -s /dev/ttyAMA0 /dev/ttyS33，即可找到/dev/ttyS33映射串口，也就对应了/dev/ttyAMA0实际串口。
修改完成后
在rxtx-2.1-7r2 内执行 
./configure 
make
sudo make install 
完成后将看到如下内容
Libraries have been installed in:
   /usr/lib/jvm/jdk-8-oracle-arm32-vfp-hflt/jre/lib/arm

.....
----------------------------------------------------------------------
/usr/bin/install -c RXTXcomm.jar /usr/lib/jvm/jdk-8-oracle-arm32-vfp-hflt/jre/lib/ext/
