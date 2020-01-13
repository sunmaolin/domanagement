# domanagement
宿舍管理系统<br>
####1）ajax请求后台通过模板解析return "index"页面不能跳转问题<br>
答：ajax在调用controller之后会自动返回到ajax中的sucess回调函数位置，因此，若我们直接在controller中进行页面跳转，则目标页面的源代码会被返回到这个success函数里，正确的页面跳转方式应该是在success函数中完成。js代码：window.location.ref=" "。