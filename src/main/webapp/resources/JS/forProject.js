$(document).ready(function() {
    $('ul#projectDetails li a').click(function(e) {
        e.preventDefault();
        var projectId = $(this).attr('data-id');

        // 使用AJAX请求来获取项目信息并设置selectedProject属性
        $.ajax({
            url: 'getProjectDetails', // 根据您的设置进行调整
            type: 'POST',
            data: { projectId: projectId },
            success: function(data) {
                // 设置selectedProject属性
                #{projectBean.setSelectedProject(data)};
            },
            error: function(error) {
                console.error('Error:', error);
            }
        });
    });
});


function displayProjectDetails(project) {
    const projectDetailsContainer = $('#projectDetails');
    const titleElement = $('<h2></h2>').text('Title: ' + project.title);
    const summaryElement = $('<h3></h3>').text('Summary: ' + project.summary);
    const descriptionElement = $('<h3></h3>').text(
        'Description: ' + project.description
    );
    const keywordsElement = $('<h3></h3>').text('Keywords: ' + project.keywords);
    const typeElement = $('<h3></h3>').text('Type: ' + project.type);
    const collaboratorsElement = $('<h3></h3>').text(
        'Collaborators: ' + project.collaborators
    );

    projectDetailsContainer
        .append(titleElement)
        .append(summaryElement)
        .append(descriptionElement)
        .append(keywordsElement)
        .append(typeElement)
        .append(collaboratorsElement);

    // 绑定双击事件来编辑项目标题
    titleElement.dblclick(function () {
        editProjectField(project, 'title', titleElement);
    });

    // 绑定双击事件来编辑项目摘要
    summaryElement.dblclick(function () {
        editProjectField(project, 'summary', summaryElement);
    });

    // 绑定双击事件来编辑项目描述
    descriptionElement.dblclick(function () {
        editProjectField(project, 'description', descriptionElement);
    });
    keywordsElement.dblclick(function () {
        editProjectField(project, 'keywords', keywordsElement);
    });
    typeElement.dblclick(function () {
        editProjectField(project, 'type', typeElement);
    });
    collaboratorsElement.dblclick(function () {
        editProjectField(project, 'collaborators', collaboratorsElement);
    });
}

function editProjectField(project, field, element) {
    const originalValue = project[field];
    element.attr('contenteditable', true);
    element.focus();

    element.blur(function () {
        const newValue = $(this).text();
        if (newValue && newValue !== originalValue) {
            project[field] = newValue;
            saveProjectData(project);
        } else {
            $(this).text(originalValue);
        }
        $(this).attr('contenteditable', false);
    });
}

function saveProjectData(project) {
    // 发送项目数据到服务器
    $.ajax({
        url: '../../src/saveProjectDataServlet', // 替换为实际的 Servlet 路径
        method: 'POST',
        data: JSON.stringify(project),
        contentType: 'application/json',
        success: function(response) {
            console.log('Successfully:', response);
        },
        error: function(error) {
            console.error('Failed：', error);
        },
    });
}
