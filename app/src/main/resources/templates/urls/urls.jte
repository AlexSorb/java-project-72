@import hexlet.code.dto.url.UrlsPage
@param UrlsPage page

@template.layout.page(
    page = page,
    content = @`
        <table data-test="urls">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Имя</th>
                    <th>Дата создания</th>
                    <th>Код ответа</th>
                </tr>
            </thead>
            <tbody>
                @for(var url : page.getUrlList())
                <tr>
                    <td>${url.getId()}</td>
                    <td><a href="/urls/${url.getId()}"> ${url.getName()}</a></td>
                    <td>2024-01-01</td>
                    <td>200</td>
                </tr>
                @endfor
            </tbody>
        </table>
    `
)