@import hexlet.code.dto.url.UrlPage

@param UrlPage page

@template.layout.page(
    page = page,
    content = @`
        <div class="table">
            <h1>Сайт: ${page.getUrl().getName()}</h1>
            <table>
              <tbody>
                <tr>
                  <th>ID</th>
                  <th> ${page.getUrl().getId()}</th>
                </tr>
                <tr>
                  <th>Имя</th>
                  <th> ${page.getUrl().getName()}</th>
                </tr>
                <tr>
                  <th>Дата создания</th>
                  <th> ${page.getUrl().getCreatedAt().toString()}</th>
                </tr>
              </tbody>
            </table>
        <div>

        <div>
              <form>
                    <h1>Проверки</h1>
                    <button type="submit" formmethod="post" formaction="/urls/${page.getUrl().getId()}/checks">Запустить проверку</button>
              </form>
        </div>
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