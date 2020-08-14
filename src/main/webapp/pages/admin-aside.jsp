<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
                     class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <a href="#"><i class="fa fa-circle text-success"></i> 已登录</a>
            </div>
        </div>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">菜单</li>
            <li id="admin-index"><a
                    href="/admin/adminmain.do"><i
                    class="fa fa-dashboard"></i> <span>首页</span></a></li>
            <!--<li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
                    <span>系统管理</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
                </span>
            </a>
                <ul class="treeview-menu">
                    <li id="system-setting">
                        <a
                        href="/user/findAll.do"> <i
                            class="fa fa-circle-o"></i> 用户管理
                    </a>
                    </li>
                    <li id="system-setting1"><a
                        href="#"> <i
                            class="fa fa-circle-o"></i> 角色管理
                    </a></li>

                </ul>

            </li>-->
        </ul>

        <ul class="treeview-menu">
            <li id="system-setting">
                <a> 图书管理</a>
                <ul class="treeview-menu">
                <li><a href="/admin/adminbook.do"><i
                        class="fa fa-circle-o"></i>全部图书</a></li>
                <li class="divider"></li>
                <li><a href="/admin/booktoadd.do"><i
                        class="fa fa-circle-o"></i>添加图书</a></li>
            </ul>
            </li>
            <li id="system-setting1">
                <a>  读者管理</a>
                <ul class="treeview-menu">
                    <li><a href="/admin/allreaders.do"><i
                            class="fa fa-circle-o"></i>全部读者</a></li>
                    <li class="divider"></li>
                    <li><a href="/admin/readertoadd.do"><i
                            class="fa fa-circle-o"></i>增加读者</a></li>
                </ul>
            </li>
            <li id="system-setting2">
                <a> 借还管理</a>
                <ul class="treeview-menu">
                    <li><a href="/admin/lendlist.do"><i
                            class="fa fa-circle-o"></i>借还日志</a></li>
                </ul>
            </li>

        </ul>
    </section>
    <!-- /.sidebar -->
</aside>