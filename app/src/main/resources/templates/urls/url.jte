@import hexlet.code.dto.url.UrlPage

@param UrlPage page

@template.layout.page(
    page = page,
    content = @`
        <h1>Сайт: ${page.getUrl().getName()}</h1>
        <div>
            <table data-test="url">
                <tbody>
                    <tr>
                        <td>ID</td>
                        <td>${page.getUrl().getId()}</td>
                    </tr>
                    <tr>
                        <td>Имя</td>
                        <td>${page.getUrl().getName()}</td>
                    </tr>
                    <tr>
                        <td>Дата создания</td>
                        <td>${page.getUrl().getCreatedAt().toString()}</td>
                    </tr>
                </tbody>
            </table>
        </div>

        <h2>Проверки</h2>
        <form action="/urls/${page.getUrl().getId()}/checks" method="post">
            <input type="submit" class="btn btn-primary btn-lg ms-3 px-5 text-uppercase mx-3" value="Запустить проверку">
        </form>

        <table data-test="checks">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Код ответа</th>
                    <th>h1</th>
                    <th>title</th>
                    <th>description</th>
                    <th>Дата создания</th>
                </tr>
            </thead>
            <tbody>
                @if(!page.getCheck().isEmpty())
                    @for(var check : page.getCheck())
                        <tr>
                            <td>${check.getId()}</td>
                            <td>${check.getStatusCode()}</td>
                            <td>${check.getH1()}</td>
                            <td>${check.getTitle()}</td>
                            <td>${check.getDescription()}</td>
                            <td>${check.getCreatedAt().toString()}</td>
                        </tr>
                    @endfor
                @endif
            </tbody>
        </table>

        `
)