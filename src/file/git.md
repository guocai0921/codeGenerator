<!-- 查看一个文件的提交历史记录 -->
git log --pretty=oneline 文件路径
git show 6b14b5883e5a03d5e2110eb78633f622e81a36ac 文件路径

添加最小配置:
git config --local user.name 'lunzi'
git config --local user.email 'lunzi@163.com'
--local,--global,--system的配置文件在哪个位置
local的在.git/config里面；global的在个人home目录下的.gitconfig里面；system应该在git安装目录的下。

清除设置
$ git config --unset --local user.name
$ git config --unset --global user.name
$ git config --unset --system user.name

设置Git大小写不敏感
git config core.ignorecase true

git log --all 查看所有分支的历史
git log --all --graph 查看图形化的 log 地址
git log --oneline 查看单行的简洁历史。
git log --oneline -n4 查看最近的四条简洁历史。
git log --oneline --all -n4 --graph 查看所有分支最近 4 条单行的图形化历史。
git help --web log 跳转到git log 的帮助文档网页
git mv text.txt text.md 为文件重命名
git有三个类型：commit、blob、tree;


git checkout -b temp <commitId> 检出分支

//cat命令主要用来查看文件内容，创建文件，文件合并，追加文件内容等功能。
cat HEAD 查看HEAD文件的内容 
git cat-file 命令 显示版本库对象的内容、类型及大小信息。
git cat-file -t b44dd71d62a5a8ed3 显示版本库对象的类型
git cat-file -s b44dd71d62a5a8ed3 显示版本库对象的大小
git cat-file -p b44dd71d62a5a8ed3 显示版本库对象的内容

HEAD：指向当前的工作路径
config：存放本地仓库（local）相关的配置信息。
refs/heads:存放分支
refs/tags:存放tag，又叫里程牌 （当这次commit是具有里程碑意义的 比如项目1.0的时候 就可以打tag）
objects：存放对象 .git/objects/ 文件夹中的子文件夹都是以哈希值的前两位字符命名 每个object由40位字符组成，前两位字符用来当文件夹，后38位做文件。

