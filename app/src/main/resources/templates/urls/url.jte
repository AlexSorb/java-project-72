@import hexlet.code.dto.url.UrlPage

@param UrlPage page

@template.layout.page(
    page = page,
    content = @`
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

        <form action="/urls/${page.getUrl().getId()}/checks" method="post">
            <h1>Проверки</h1>
            <input type="submit" class="btn btn-primary btn-lg ms-3 px-5 text-uppercase mx-3" value="Запустить проверку">
        </form>
        <div class="table_checks">
                    <table>
                      <thead>
                        <tr>
                          <th>ID</th>
                          <th>Код ответа</th>
                          <th>title</th>
                          <th>h1</th>
                          <th>description</th>
                          <th>Дата проверки</th>
                        </tr>
                      </thead>

                      <tbody>
                        @if(!page.getCheck().isEmpty())
                         @for(var check : page.getCheck())
                           <tr>
                             <td>${check.getId()}</td>
                             <td>${check.getStatusCode()}</td>
                             <td>${check.getTitle()}</td>
                             <td>${check.getH1()}</td>
                             <td>${check.getDescription()}</td>
                             <td>${check.getCreatedAt().toString()}</td>
                           </tr>
                         @endfor
                        @endif
                      </tbody>
                    </table>
                <div>

        `
)