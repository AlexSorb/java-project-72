@import hexlet.code.dto.url.UrlsPage
@param UrlsPage page

@template.layout.page(
    page = page,
    content = @`
         <div class="index_block">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Имя</th>
                        <th>Последняя проверка</th>
                        <th>Код ответа</th>
                    </tr>
                </thead>
                <tbody>
                    @for(var url : page.getUrlList())
                        <tr>
                            <td>${url.getId()}</td>
                            <td><a href="/urls/${url.getId()}"> ${url.getName()}</a></td>
                            <td>   </td>
                            <td>   </td>
                        </tr>
                    @endfor
                </tbody>
            </table>
        </div>
    `
)